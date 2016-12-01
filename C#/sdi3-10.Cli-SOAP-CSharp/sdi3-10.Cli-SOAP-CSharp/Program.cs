using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using sdi3_10.Cli_SOAP_CSharp.RatingsService;
using sdi3_10.Cli_SOAP_CSharp.SeatsService;
using sdi3_10.Cli_SOAP_CSharp.TripsService;
using sdi3_10.Cli_SOAP_CSharp.UsersService;


namespace sdi3_10.Cli_SOAP_CSharp
{
    class Program
    {
        static void Main(string[] args)
        {
            string opcion = null;
            while (opcion != "0")
                opcion = procesarOpcion(opcion);
        }

        private static string procesarOpcion(string opcion)
        {
            Console.WriteLine("Seleccione una opcion para administrar el sistema");
            Console.WriteLine("1-Listar usuarios \n 2-Deshabilitar usuarios " +
                                "\n 3-Listar comenterios y puntuaciones del ultimo mes \n " +
                                "4-Eliminar comentarios y puntuaciones \n 0-Salir");
            opcion = Console.ReadLine();
            switch (opcion)
            {
                case "0":
                    Console.WriteLine("Pulse una tecla para finalizar");
                    Console.ReadKey();
                    break;
                case "1":
                    ListarUsuarios();
                    break;
                case "2":
                    DeshabilitarUsuario();
                    break;
                case "3":
                    ListarComentariosYPuntuacionesUltimoMes();
                    break;
                case "4":
                    EliminarComentariosYPuntuaciones();
                    break;
                default:
                    break;
            }
            return opcion;
        }

        private static void EliminarComentariosYPuntuaciones()
        {
            EjbRatingsServiceService ejbRatings = new EjbRatingsServiceService();

            rating[] ratings = ejbRatings.findAll();
            Console.WriteLine("Lista de los comentarios del sistema");
            foreach (rating r in ratings)
            {
                ImprimirComentarios(r);
            }
            Boolean condicion = false;
            while (!condicion)
            {
                Console.WriteLine("Seleccione el id de uno de los comentarios a borrar");
                long id = long.Parse(Console.ReadLine());
                ejbRatings.remove(id, true);
                Console.WriteLine("Si desea eliminar algun comentario mas diga Si en caso contrario No");
                string eleccion = Console.ReadLine();
                if (eleccion.Equals("No"))
                    condicion = true;
                else
                {
                    ratings = ejbRatings.findAll();
                    Console.WriteLine("Lista de los comentarios del sistema");
                    foreach (rating r in ratings)
                    {
                        ImprimirComentarios(r);
                    }
                }
            }
        }

        private static void ListarComentariosYPuntuacionesUltimoMes()
        {
            EjbRatingsServiceService ejbRatings = new EjbRatingsServiceService();

            rating[] ratings = ejbRatings.findByLastMonth();

            Console.WriteLine("Ratings del ultimo mes");
            if (ratings != null)
            {
                foreach (rating r in ratings)
                {
                    ImprimirComentarios(r);
                }
            }
            else
            {
                Console.WriteLine("No hay ratings el mes pasado");
            }
        }

        private static void ImprimirComentarios(rating r)
        {
            Console.WriteLine("Del usuario: " + r.seatFromUserId + " al usuario: " + r.seatAboutUserId + " en el viaje: " + 
                r.seatAboutTripId + " comentario: " + r.comment + 
                " y puntuacion: " + r.value);
        }

        private static void DeshabilitarUsuario()
        {
            EjbUsersServiceService ejbUser = new EjbUsersServiceService();
            EjbTripsServiceService ejbTrip = new EjbTripsServiceService();
            EjbSeatsServiceService ejbSeat = new EjbSeatsServiceService();

            user[] users = ejbUser.findAllActive();
            foreach (user u in users)
            {
                Console.WriteLine("id: " + u.id + " login: " + u.login);
            }
            Console.WriteLine("Introduzca la id del usuario a cancelar");
            long id = long.Parse(Console.ReadLine());
            ejbUser.cancelUser(id, true);
            Console.WriteLine("Viajes cancelados al cancelar al usuario con la id: " + id);

            trip[] cancelados = ejbTrip.findByPromoter(id, true);
            seat[] excluidos = ejbSeat.findByUserId(id, true);
            Console.WriteLine("Viajes cancelados al cancelar al usuario con el id: "
            + id);
            foreach (trip t in cancelados)
            {
                Console.WriteLine("id: " + t.id + " salida: " + t.departure + " destino: " + t.destination);
            }
            Console.WriteLine("Asientos del que ha sido excluido el usuario cancelado con el id: "
                + id);
            foreach (seat s in excluidos)
            {
                Console.WriteLine("trip id: " + s.tripId + " user_id: " + s.userId);
            }
        }

        private static void ListarUsuarios()
        {
            EjbUsersServiceService ejbUser = new EjbUsersServiceService();
            EjbTripsServiceService ejbTrip = new EjbTripsServiceService();
            user[] users = ejbUser.findAllActive();
            foreach (user u in users)
            {
                listadoDTO dto = ejbTrip.findPromoterOrParticipated(u.id, true);
                Console.WriteLine("Datos personales");
                Console.WriteLine("Login: " + u.login + "\nNombre: "
                        + u.name + "\nApellidos: " + u.surname);
                Console.WriteLine("Viajes promotor: "
                        + dto.viajesPromoter.Length);
                Console.WriteLine("Viajes participa: " + dto.viajesSeat.Length);
                Console.WriteLine("Viajes totales : " + dto.viajes.Length);
            }
        }
    }
}

