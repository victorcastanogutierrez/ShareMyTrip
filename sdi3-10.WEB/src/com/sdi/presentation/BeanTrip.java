package com.sdi.presentation;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.sdi.business.TripsService;
import com.sdi.infrastructure.Factories;
import com.sdi.model.AddressPoint;
import com.sdi.model.Trip;
import com.sdi.model.TripStatus;
import com.sdi.model.User;
import com.sdi.model.Waypoint;
import com.sdi.presentation.util.MessagesUtil;

/**
 * Bean Session que se encarga de un viaje
 * 
 * @author sdi2-10
 * 
 */
@ManagedBean(name = "trip")
@SessionScoped
public class BeanTrip implements Serializable {

	private static final long serialVersionUID = 55556L;

	private Long id;

	private String departureaddress;
	private String departurecity;
	private String departurestate;
	private String departurecountry;
	private String departurezipCode;
	private Double departurelat;
	private Double departurelon;

	private String arrivaladdress;
	private String arrivalcity;
	private String arrivalstate;
	private String arrivalcountry;
	private String arrivalzipCode;
	private Double arrivallat;
	private Double arrivallon;

	private Date arrivalDate;
	private Date departureDate;
	private Date closingDate;
	private Integer availablePax = 0;
	private Integer maxPax = 0;
	private Double estimatedCost = 0.0;
	private String comments;
	private TripStatus status;
	private Long promoterId;

	private Date fechaActual = new Date();

	public BeanTrip() {
	}

	/**
	 * Método que crea un viaje nuevo o actualiza el existente en caso de estar
	 * editándose
	 * 
	 * @return outcome de la acción
	 */
	public String salva() {
		ResourceBundle b = MessagesUtil.getResourceBoundle();
		if (!assertFechasCoherentes(departureDate, arrivalDate, closingDate)) {
			crearMensajeAdvert(b.getString("warningmesFechaViajes"));
			return "error";
		}
		TripsService service = Factories.services.getTripsService();
		Long promoter = obtainPromoter();
		AddressPoint salida = extractAddressPoint(departureaddress,
				departurecity, departurestate, departurecountry,
				departurezipCode, departurelat, departurelon);
		AddressPoint destino = extractAddressPoint(arrivaladdress, arrivalcity,
				arrivalstate, arrivalcountry, arrivalzipCode, arrivallat,
				arrivallon);
		Trip trip = new Trip(salida, destino, arrivalDate, departureDate,
				closingDate, availablePax, maxPax, estimatedCost, comments,
				TripStatus.OPEN, promoter);
		// Editamos
		if (getId() != null) {
			trip.setId(getId());
			service.update(trip);
			trip.setId(null);
			return "exito";
		}
		// Guardamos nuevo viaje
		if (service.registerTrip(trip) == null) {
			crearMensajeAdvert(b.getString("warningViajeDuplicado"));
			return "error";
		}
		return "exito";
	}

	/**
	 * Método auxiliar que crea un mensaje de error para el usuario con el
	 * contenido especificado por parámetro
	 * 
	 * @param msError
	 *            String con el contenido
	 */
	private void crearMensajeAdvert(String msError) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "", msError));
	}

	/**
	 * Método auxiliar que comprueba que las fechas de un viaje sean coherentes.
	 * Ésto es que la de cierre sea inferior a la de salida, y a su vez que la
	 * de salida sea inferior a la de llegada. Además la de cierre debe ser no
	 * inferior a la fecha actual.
	 * 
	 * @param departure
	 *            fecha de salida
	 * @param arrival
	 *            fecha de llegada
	 * @param closing
	 *            fecha de cierre
	 * @return true en caso de ser correctas, false si no
	 */
	private boolean assertFechasCoherentes(Date departure, Date arrival,
			Date closing) {
		return closing.before(departure) && departure.before(arrival)
				&& closing.after(new Date());
	}

	/**
	 * Método que obtiene el DTO addressPoint construido por sus atributos
	 * pasados por parámetro
	 * 
	 * @param calleSalida
	 * @param ciudadSalida
	 * @param provinciaSalida
	 * @param paisSalida
	 * @param zipcodeSalida
	 * @param cxSalida
	 * @param cySalida
	 * @return el DTO addressPoint con toda la información
	 */
	private AddressPoint extractAddressPoint(String calleSalida,
			String ciudadSalida, String provinciaSalida, String paisSalida,
			String zipcodeSalida, Double cxSalida, Double cySalida) {

		return new AddressPoint(calleSalida, ciudadSalida, provinciaSalida,
				paisSalida, zipcodeSalida, new Waypoint(cxSalida, cySalida));
	}

	/**
	 * Método auxiliar que obtiene la ID del usuario logueado en el sistema
	 * 
	 * @return la id obtenida
	 */
	private Long obtainPromoter() {
		Map<String, Object> session = FacesContext.getCurrentInstance()
				.getExternalContext().getSessionMap();
		Long promoter = ((User) session.get("LOGGEDIN_USER")).getId();
		return promoter;
	}

	/**
	 * Método que inicializa los valores de un viaje.
	 * 
	 * @param trip
	 *            viaje con los datos
	 */
	public void setTrip(Trip trip) {
		setId(trip.getId());
		setArrivalDate(trip.getArrivalDate());
		setAvailablePax(trip.getAvailablePax());
		setClosingDate(trip.getClosingDate());
		setComments(trip.getComments());

		setArrivaladdress(trip.getDestination().getAddress());
		setArrivalcity(trip.getDestination().getCity());
		setArrivalcountry(trip.getDestination().getCountry());
		setArrivalstate(trip.getDestination().getState());
		setArrivalzipCode(trip.getDestination().getZipCode());
		setArrivallat(trip.getDestination().getWaypoint().getLat());
		setArrivallon(trip.getDestination().getWaypoint().getLon());

		setDepartureaddress(trip.getDeparture().getAddress());
		setDeparturecity(trip.getDeparture().getCity());
		setDeparturecountry(trip.getDeparture().getCountry());
		setDeparturestate(trip.getDeparture().getState());
		setDeparturezipCode(trip.getDeparture().getZipCode());
		setDeparturelat(trip.getDeparture().getWaypoint().getLat());
		setDeparturelon(trip.getDeparture().getWaypoint().getLon());

		setDepartureDate(trip.getDepartureDate());
		setEstimatedCost(trip.getEstimatedCost());
		setId(trip.getId());
		setMaxPax(trip.getMaxPax());
		setPromoterId(trip.getPromoterId());
		setStatus(trip.getStatus());
	}

	/**
	 * Método que carga en el viaje todos los datos por defecto y válidos para
	 * ser enviados como un nuevo viaje
	 * 
	 * @return null para evitar problemas con navegadores con JS desactivado
	 */
	public String valoresdefecto() {
		ResourceBundle b = MessagesUtil.getResourceBoundle();

		Calendar c = Calendar.getInstance();
		Date d = new Date();
		c.setTime(d);

		c.add(Calendar.DAY_OF_YEAR, 1);
		setClosingDate(c.getTime());
		c.add(Calendar.DAY_OF_YEAR, 1);
		setDepartureDate(c.getTime());
		c.add(Calendar.DAY_OF_YEAR, 1);
		setArrivalDate(c.getTime());

		setAvailablePax(Integer.parseInt(b
				.getString("precalViajePlazasDisponibles")));
		setComments(b.getString("precalViajeComentarios"));
		setArrivaladdress(b.getString("precalViajeCalleDestino"));
		setArrivalcity(b.getString("precalViajeCiudadDestino"));
		setArrivalcountry(b.getString("precalViajePaisDestino"));
		setArrivalzipCode(b.getString("precalViajeCodigoPostalDestino"));
		setArrivallat(Double.parseDouble(b
				.getString("precalViajeCoordenadaYDestino")));
		setArrivallon(Double.parseDouble(b
				.getString("precalViajeCoordenadaXDestino")));
		setDepartureaddress(b.getString("precalViajeCalleSalida"));
		setDeparturecity(b.getString("precalViajeCiudadSalida"));
		setDeparturestate(b.getString("precalViajeProvinciaSalida"));
		setArrivalstate(b.getString("precalViajeProvinciaDestino"));
		setDeparturecountry(b.getString("precalViajePaisSalida"));
		setDeparturezipCode(b.getString("precalViajeCodigoPostalSalida"));
		setDeparturelat(Double.parseDouble(b
				.getString("precalViajeCoodenadaXSalida")));
		setDeparturelon(Double.parseDouble(b
				.getString("precalViajeCoodenadaYSalida")));
		setEstimatedCost(Double.parseDouble(b
				.getString("precalViajeCosteEstimado")));
		setMaxPax(Integer.parseInt(b.getString("precalViajePlazasTotales")));
		return null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public Date getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(Date closingDate) {
		this.closingDate = closingDate;
	}

	public Integer getAvailablePax() {
		return availablePax;
	}

	public void setAvailablePax(Integer availablePax) {
		this.availablePax = availablePax;
	}

	public Integer getMaxPax() {
		return maxPax;
	}

	public void setMaxPax(Integer maxPax) {
		this.maxPax = maxPax;
	}

	public Double getEstimatedCost() {
		return estimatedCost;
	}

	public void setEstimatedCost(Double estimatedCost) {
		this.estimatedCost = estimatedCost;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public TripStatus getStatus() {
		return status;
	}

	public void setStatus(TripStatus status) {
		this.status = status;
	}

	public Long getPromoterId() {
		return promoterId;
	}

	public void setPromoterId(Long promoterId) {
		this.promoterId = promoterId;
	}

	public Date getFechaActual() {
		return fechaActual;
	}

	public void setFechaActual(Date fechaActual) {
		this.fechaActual = fechaActual;
	}

	public String getDepartureaddress() {
		return departureaddress;
	}

	public void setDepartureaddress(String departureaddress) {
		this.departureaddress = departureaddress;
	}

	public String getDeparturecity() {
		return departurecity;
	}

	public void setDeparturecity(String departurecity) {
		this.departurecity = departurecity;
	}

	public String getDeparturestate() {
		return departurestate;
	}

	public void setDeparturestate(String departurestate) {
		this.departurestate = departurestate;
	}

	public String getDeparturecountry() {
		return departurecountry;
	}

	public void setDeparturecountry(String departurecountry) {
		this.departurecountry = departurecountry;
	}

	public String getDeparturezipCode() {
		return departurezipCode;
	}

	public void setDeparturezipCode(String departurezipCode) {
		this.departurezipCode = departurezipCode;
	}

	public String getArrivaladdress() {
		return arrivaladdress;
	}

	public void setArrivaladdress(String arrivaladdress) {
		this.arrivaladdress = arrivaladdress;
	}

	public String getArrivalcity() {
		return arrivalcity;
	}

	public void setArrivalcity(String arrivalcity) {
		this.arrivalcity = arrivalcity;
	}

	public String getArrivalstate() {
		return arrivalstate;
	}

	public void setArrivalstate(String arrivalstate) {
		this.arrivalstate = arrivalstate;
	}

	public String getArrivalcountry() {
		return arrivalcountry;
	}

	public void setArrivalcountry(String arrivalcountry) {
		this.arrivalcountry = arrivalcountry;
	}

	public String getArrivalzipCode() {
		return arrivalzipCode;
	}

	public void setArrivalzipCode(String arrivalzipCode) {
		this.arrivalzipCode = arrivalzipCode;
	}

	public Double getDeparturelat() {
		return departurelat;
	}

	public void setDeparturelat(Double departurelat) {
		this.departurelat = departurelat;
	}

	public Double getDeparturelon() {
		return departurelon;
	}

	public void setDeparturelon(Double departurelon) {
		this.departurelon = departurelon;
	}

	public Double getArrivallat() {
		return arrivallat;
	}

	public void setArrivallat(Double arrivallat) {
		this.arrivallat = arrivallat;
	}

	public Double getArrivallon() {
		return arrivallon;
	}

	public void setArrivallon(Double arrivallon) {
		this.arrivallon = arrivallon;
	}

}
