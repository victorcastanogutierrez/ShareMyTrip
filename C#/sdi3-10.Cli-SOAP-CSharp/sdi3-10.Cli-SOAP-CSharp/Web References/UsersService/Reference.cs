﻿//------------------------------------------------------------------------------
// <auto-generated>
//     Este código fue generado por una herramienta.
//     Versión de runtime:4.0.30319.42000
//
//     Los cambios en este archivo podrían causar un comportamiento incorrecto y se perderán si
//     se vuelve a generar el código.
// </auto-generated>
//------------------------------------------------------------------------------

// 
// Microsoft.VSDesigner generó automáticamente este código fuente, versión=4.0.30319.42000.
// 
#pragma warning disable 1591

namespace sdi3_10.Cli_SOAP_CSharp.UsersService {
    using System;
    using System.Web.Services;
    using System.Diagnostics;
    using System.Web.Services.Protocols;
    using System.Xml.Serialization;
    using System.ComponentModel;
    
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Web.Services", "4.6.1038.0")]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.ComponentModel.DesignerCategoryAttribute("code")]
    [System.Web.Services.WebServiceBindingAttribute(Name="EjbUsersServiceServiceSoapBinding", Namespace="http://impl.business.sdi.com/")]
    public partial class EjbUsersServiceService : System.Web.Services.Protocols.SoapHttpClientProtocol {
        
        private System.Threading.SendOrPostCallback findByIdOperationCompleted;
        
        private System.Threading.SendOrPostCallback findAllOperationCompleted;
        
        private System.Threading.SendOrPostCallback findAllActiveOperationCompleted;
        
        private System.Threading.SendOrPostCallback cancelUserOperationCompleted;
        
        private System.Threading.SendOrPostCallback findByLoginOperationCompleted;
        
        private System.Threading.SendOrPostCallback listByAcceptedAndPendingOperationCompleted;
        
        private System.Threading.SendOrPostCallback registerOperationCompleted;
        
        private System.Threading.SendOrPostCallback listByTripAndSeatStatusOperationCompleted;
        
        private System.Threading.SendOrPostCallback findByEmailOperationCompleted;
        
        private bool useDefaultCredentialsSetExplicitly;
        
        /// <remarks/>
        public EjbUsersServiceService() {
            this.Url = global::sdi3_10.Cli_SOAP_CSharp.Properties.Settings.Default.sdi3_10_Cli_SOAP_CSharp_UsersService_EjbUsersServiceService;
            if ((this.IsLocalFileSystemWebService(this.Url) == true)) {
                this.UseDefaultCredentials = true;
                this.useDefaultCredentialsSetExplicitly = false;
            }
            else {
                this.useDefaultCredentialsSetExplicitly = true;
            }
        }
        
        public new string Url {
            get {
                return base.Url;
            }
            set {
                if ((((this.IsLocalFileSystemWebService(base.Url) == true) 
                            && (this.useDefaultCredentialsSetExplicitly == false)) 
                            && (this.IsLocalFileSystemWebService(value) == false))) {
                    base.UseDefaultCredentials = false;
                }
                base.Url = value;
            }
        }
        
        public new bool UseDefaultCredentials {
            get {
                return base.UseDefaultCredentials;
            }
            set {
                base.UseDefaultCredentials = value;
                this.useDefaultCredentialsSetExplicitly = true;
            }
        }
        
        /// <remarks/>
        public event findByIdCompletedEventHandler findByIdCompleted;
        
        /// <remarks/>
        public event findAllCompletedEventHandler findAllCompleted;
        
        /// <remarks/>
        public event findAllActiveCompletedEventHandler findAllActiveCompleted;
        
        /// <remarks/>
        public event cancelUserCompletedEventHandler cancelUserCompleted;
        
        /// <remarks/>
        public event findByLoginCompletedEventHandler findByLoginCompleted;
        
        /// <remarks/>
        public event listByAcceptedAndPendingCompletedEventHandler listByAcceptedAndPendingCompleted;
        
        /// <remarks/>
        public event registerCompletedEventHandler registerCompleted;
        
        /// <remarks/>
        public event listByTripAndSeatStatusCompletedEventHandler listByTripAndSeatStatusCompleted;
        
        /// <remarks/>
        public event findByEmailCompletedEventHandler findByEmailCompleted;
        
        /// <remarks/>
        [System.Web.Services.Protocols.SoapDocumentMethodAttribute("", RequestNamespace="http://impl.business.sdi.com/", ResponseNamespace="http://impl.business.sdi.com/", Use=System.Web.Services.Description.SoapBindingUse.Literal, ParameterStyle=System.Web.Services.Protocols.SoapParameterStyle.Wrapped)]
        [return: System.Xml.Serialization.XmlElementAttribute("return", Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
        public user findById([System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)] long arg0, [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)] [System.Xml.Serialization.XmlIgnoreAttribute()] bool arg0Specified) {
            object[] results = this.Invoke("findById", new object[] {
                        arg0,
                        arg0Specified});
            return ((user)(results[0]));
        }
        
        /// <remarks/>
        public void findByIdAsync(long arg0, bool arg0Specified) {
            this.findByIdAsync(arg0, arg0Specified, null);
        }
        
        /// <remarks/>
        public void findByIdAsync(long arg0, bool arg0Specified, object userState) {
            if ((this.findByIdOperationCompleted == null)) {
                this.findByIdOperationCompleted = new System.Threading.SendOrPostCallback(this.OnfindByIdOperationCompleted);
            }
            this.InvokeAsync("findById", new object[] {
                        arg0,
                        arg0Specified}, this.findByIdOperationCompleted, userState);
        }
        
        private void OnfindByIdOperationCompleted(object arg) {
            if ((this.findByIdCompleted != null)) {
                System.Web.Services.Protocols.InvokeCompletedEventArgs invokeArgs = ((System.Web.Services.Protocols.InvokeCompletedEventArgs)(arg));
                this.findByIdCompleted(this, new findByIdCompletedEventArgs(invokeArgs.Results, invokeArgs.Error, invokeArgs.Cancelled, invokeArgs.UserState));
            }
        }
        
        /// <remarks/>
        [System.Web.Services.Protocols.SoapDocumentMethodAttribute("", RequestNamespace="http://impl.business.sdi.com/", ResponseNamespace="http://impl.business.sdi.com/", Use=System.Web.Services.Description.SoapBindingUse.Literal, ParameterStyle=System.Web.Services.Protocols.SoapParameterStyle.Wrapped)]
        [return: System.Xml.Serialization.XmlElementAttribute("return", Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
        public user[] findAll() {
            object[] results = this.Invoke("findAll", new object[0]);
            return ((user[])(results[0]));
        }
        
        /// <remarks/>
        public void findAllAsync() {
            this.findAllAsync(null);
        }
        
        /// <remarks/>
        public void findAllAsync(object userState) {
            if ((this.findAllOperationCompleted == null)) {
                this.findAllOperationCompleted = new System.Threading.SendOrPostCallback(this.OnfindAllOperationCompleted);
            }
            this.InvokeAsync("findAll", new object[0], this.findAllOperationCompleted, userState);
        }
        
        private void OnfindAllOperationCompleted(object arg) {
            if ((this.findAllCompleted != null)) {
                System.Web.Services.Protocols.InvokeCompletedEventArgs invokeArgs = ((System.Web.Services.Protocols.InvokeCompletedEventArgs)(arg));
                this.findAllCompleted(this, new findAllCompletedEventArgs(invokeArgs.Results, invokeArgs.Error, invokeArgs.Cancelled, invokeArgs.UserState));
            }
        }
        
        /// <remarks/>
        [System.Web.Services.Protocols.SoapDocumentMethodAttribute("", RequestNamespace="http://impl.business.sdi.com/", ResponseNamespace="http://impl.business.sdi.com/", Use=System.Web.Services.Description.SoapBindingUse.Literal, ParameterStyle=System.Web.Services.Protocols.SoapParameterStyle.Wrapped)]
        [return: System.Xml.Serialization.XmlElementAttribute("return", Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
        public user[] findAllActive() {
            object[] results = this.Invoke("findAllActive", new object[0]);
            return ((user[])(results[0]));
        }
        
        /// <remarks/>
        public void findAllActiveAsync() {
            this.findAllActiveAsync(null);
        }
        
        /// <remarks/>
        public void findAllActiveAsync(object userState) {
            if ((this.findAllActiveOperationCompleted == null)) {
                this.findAllActiveOperationCompleted = new System.Threading.SendOrPostCallback(this.OnfindAllActiveOperationCompleted);
            }
            this.InvokeAsync("findAllActive", new object[0], this.findAllActiveOperationCompleted, userState);
        }
        
        private void OnfindAllActiveOperationCompleted(object arg) {
            if ((this.findAllActiveCompleted != null)) {
                System.Web.Services.Protocols.InvokeCompletedEventArgs invokeArgs = ((System.Web.Services.Protocols.InvokeCompletedEventArgs)(arg));
                this.findAllActiveCompleted(this, new findAllActiveCompletedEventArgs(invokeArgs.Results, invokeArgs.Error, invokeArgs.Cancelled, invokeArgs.UserState));
            }
        }
        
        /// <remarks/>
        [System.Web.Services.Protocols.SoapDocumentMethodAttribute("", RequestNamespace="http://impl.business.sdi.com/", ResponseNamespace="http://impl.business.sdi.com/", Use=System.Web.Services.Description.SoapBindingUse.Literal, ParameterStyle=System.Web.Services.Protocols.SoapParameterStyle.Wrapped)]
        public void cancelUser([System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)] long arg0, [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)] [System.Xml.Serialization.XmlIgnoreAttribute()] bool arg0Specified) {
            this.Invoke("cancelUser", new object[] {
                        arg0,
                        arg0Specified});
        }
        
        /// <remarks/>
        public void cancelUserAsync(long arg0, bool arg0Specified) {
            this.cancelUserAsync(arg0, arg0Specified, null);
        }
        
        /// <remarks/>
        public void cancelUserAsync(long arg0, bool arg0Specified, object userState) {
            if ((this.cancelUserOperationCompleted == null)) {
                this.cancelUserOperationCompleted = new System.Threading.SendOrPostCallback(this.OncancelUserOperationCompleted);
            }
            this.InvokeAsync("cancelUser", new object[] {
                        arg0,
                        arg0Specified}, this.cancelUserOperationCompleted, userState);
        }
        
        private void OncancelUserOperationCompleted(object arg) {
            if ((this.cancelUserCompleted != null)) {
                System.Web.Services.Protocols.InvokeCompletedEventArgs invokeArgs = ((System.Web.Services.Protocols.InvokeCompletedEventArgs)(arg));
                this.cancelUserCompleted(this, new System.ComponentModel.AsyncCompletedEventArgs(invokeArgs.Error, invokeArgs.Cancelled, invokeArgs.UserState));
            }
        }
        
        /// <remarks/>
        [System.Web.Services.Protocols.SoapDocumentMethodAttribute("", RequestNamespace="http://impl.business.sdi.com/", ResponseNamespace="http://impl.business.sdi.com/", Use=System.Web.Services.Description.SoapBindingUse.Literal, ParameterStyle=System.Web.Services.Protocols.SoapParameterStyle.Wrapped)]
        [return: System.Xml.Serialization.XmlElementAttribute("return", Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
        public user findByLogin([System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)] string arg0) {
            object[] results = this.Invoke("findByLogin", new object[] {
                        arg0});
            return ((user)(results[0]));
        }
        
        /// <remarks/>
        public void findByLoginAsync(string arg0) {
            this.findByLoginAsync(arg0, null);
        }
        
        /// <remarks/>
        public void findByLoginAsync(string arg0, object userState) {
            if ((this.findByLoginOperationCompleted == null)) {
                this.findByLoginOperationCompleted = new System.Threading.SendOrPostCallback(this.OnfindByLoginOperationCompleted);
            }
            this.InvokeAsync("findByLogin", new object[] {
                        arg0}, this.findByLoginOperationCompleted, userState);
        }
        
        private void OnfindByLoginOperationCompleted(object arg) {
            if ((this.findByLoginCompleted != null)) {
                System.Web.Services.Protocols.InvokeCompletedEventArgs invokeArgs = ((System.Web.Services.Protocols.InvokeCompletedEventArgs)(arg));
                this.findByLoginCompleted(this, new findByLoginCompletedEventArgs(invokeArgs.Results, invokeArgs.Error, invokeArgs.Cancelled, invokeArgs.UserState));
            }
        }
        
        /// <remarks/>
        [System.Web.Services.Protocols.SoapDocumentMethodAttribute("", RequestNamespace="http://impl.business.sdi.com/", ResponseNamespace="http://impl.business.sdi.com/", Use=System.Web.Services.Description.SoapBindingUse.Literal, ParameterStyle=System.Web.Services.Protocols.SoapParameterStyle.Wrapped)]
        [return: System.Xml.Serialization.XmlElementAttribute("return", Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
        public user[] listByAcceptedAndPending([System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)] long arg0, [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)] [System.Xml.Serialization.XmlIgnoreAttribute()] bool arg0Specified) {
            object[] results = this.Invoke("listByAcceptedAndPending", new object[] {
                        arg0,
                        arg0Specified});
            return ((user[])(results[0]));
        }
        
        /// <remarks/>
        public void listByAcceptedAndPendingAsync(long arg0, bool arg0Specified) {
            this.listByAcceptedAndPendingAsync(arg0, arg0Specified, null);
        }
        
        /// <remarks/>
        public void listByAcceptedAndPendingAsync(long arg0, bool arg0Specified, object userState) {
            if ((this.listByAcceptedAndPendingOperationCompleted == null)) {
                this.listByAcceptedAndPendingOperationCompleted = new System.Threading.SendOrPostCallback(this.OnlistByAcceptedAndPendingOperationCompleted);
            }
            this.InvokeAsync("listByAcceptedAndPending", new object[] {
                        arg0,
                        arg0Specified}, this.listByAcceptedAndPendingOperationCompleted, userState);
        }
        
        private void OnlistByAcceptedAndPendingOperationCompleted(object arg) {
            if ((this.listByAcceptedAndPendingCompleted != null)) {
                System.Web.Services.Protocols.InvokeCompletedEventArgs invokeArgs = ((System.Web.Services.Protocols.InvokeCompletedEventArgs)(arg));
                this.listByAcceptedAndPendingCompleted(this, new listByAcceptedAndPendingCompletedEventArgs(invokeArgs.Results, invokeArgs.Error, invokeArgs.Cancelled, invokeArgs.UserState));
            }
        }
        
        /// <remarks/>
        [System.Web.Services.Protocols.SoapDocumentMethodAttribute("", RequestNamespace="http://impl.business.sdi.com/", ResponseNamespace="http://impl.business.sdi.com/", Use=System.Web.Services.Description.SoapBindingUse.Literal, ParameterStyle=System.Web.Services.Protocols.SoapParameterStyle.Wrapped)]
        [return: System.Xml.Serialization.XmlElementAttribute("return", Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
        public user register([System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)] user arg0) {
            object[] results = this.Invoke("register", new object[] {
                        arg0});
            return ((user)(results[0]));
        }
        
        /// <remarks/>
        public void registerAsync(user arg0) {
            this.registerAsync(arg0, null);
        }
        
        /// <remarks/>
        public void registerAsync(user arg0, object userState) {
            if ((this.registerOperationCompleted == null)) {
                this.registerOperationCompleted = new System.Threading.SendOrPostCallback(this.OnregisterOperationCompleted);
            }
            this.InvokeAsync("register", new object[] {
                        arg0}, this.registerOperationCompleted, userState);
        }
        
        private void OnregisterOperationCompleted(object arg) {
            if ((this.registerCompleted != null)) {
                System.Web.Services.Protocols.InvokeCompletedEventArgs invokeArgs = ((System.Web.Services.Protocols.InvokeCompletedEventArgs)(arg));
                this.registerCompleted(this, new registerCompletedEventArgs(invokeArgs.Results, invokeArgs.Error, invokeArgs.Cancelled, invokeArgs.UserState));
            }
        }
        
        /// <remarks/>
        [System.Web.Services.Protocols.SoapDocumentMethodAttribute("", RequestNamespace="http://impl.business.sdi.com/", ResponseNamespace="http://impl.business.sdi.com/", Use=System.Web.Services.Description.SoapBindingUse.Literal, ParameterStyle=System.Web.Services.Protocols.SoapParameterStyle.Wrapped)]
        [return: System.Xml.Serialization.XmlElementAttribute("return", Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
        public user[] listByTripAndSeatStatus([System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)] long arg0, [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)] [System.Xml.Serialization.XmlIgnoreAttribute()] bool arg0Specified, [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)] seatStatus arg1, [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)] [System.Xml.Serialization.XmlIgnoreAttribute()] bool arg1Specified) {
            object[] results = this.Invoke("listByTripAndSeatStatus", new object[] {
                        arg0,
                        arg0Specified,
                        arg1,
                        arg1Specified});
            return ((user[])(results[0]));
        }
        
        /// <remarks/>
        public void listByTripAndSeatStatusAsync(long arg0, bool arg0Specified, seatStatus arg1, bool arg1Specified) {
            this.listByTripAndSeatStatusAsync(arg0, arg0Specified, arg1, arg1Specified, null);
        }
        
        /// <remarks/>
        public void listByTripAndSeatStatusAsync(long arg0, bool arg0Specified, seatStatus arg1, bool arg1Specified, object userState) {
            if ((this.listByTripAndSeatStatusOperationCompleted == null)) {
                this.listByTripAndSeatStatusOperationCompleted = new System.Threading.SendOrPostCallback(this.OnlistByTripAndSeatStatusOperationCompleted);
            }
            this.InvokeAsync("listByTripAndSeatStatus", new object[] {
                        arg0,
                        arg0Specified,
                        arg1,
                        arg1Specified}, this.listByTripAndSeatStatusOperationCompleted, userState);
        }
        
        private void OnlistByTripAndSeatStatusOperationCompleted(object arg) {
            if ((this.listByTripAndSeatStatusCompleted != null)) {
                System.Web.Services.Protocols.InvokeCompletedEventArgs invokeArgs = ((System.Web.Services.Protocols.InvokeCompletedEventArgs)(arg));
                this.listByTripAndSeatStatusCompleted(this, new listByTripAndSeatStatusCompletedEventArgs(invokeArgs.Results, invokeArgs.Error, invokeArgs.Cancelled, invokeArgs.UserState));
            }
        }
        
        /// <remarks/>
        [System.Web.Services.Protocols.SoapDocumentMethodAttribute("", RequestNamespace="http://impl.business.sdi.com/", ResponseNamespace="http://impl.business.sdi.com/", Use=System.Web.Services.Description.SoapBindingUse.Literal, ParameterStyle=System.Web.Services.Protocols.SoapParameterStyle.Wrapped)]
        [return: System.Xml.Serialization.XmlElementAttribute("return", Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
        public user findByEmail([System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)] string arg0) {
            object[] results = this.Invoke("findByEmail", new object[] {
                        arg0});
            return ((user)(results[0]));
        }
        
        /// <remarks/>
        public void findByEmailAsync(string arg0) {
            this.findByEmailAsync(arg0, null);
        }
        
        /// <remarks/>
        public void findByEmailAsync(string arg0, object userState) {
            if ((this.findByEmailOperationCompleted == null)) {
                this.findByEmailOperationCompleted = new System.Threading.SendOrPostCallback(this.OnfindByEmailOperationCompleted);
            }
            this.InvokeAsync("findByEmail", new object[] {
                        arg0}, this.findByEmailOperationCompleted, userState);
        }
        
        private void OnfindByEmailOperationCompleted(object arg) {
            if ((this.findByEmailCompleted != null)) {
                System.Web.Services.Protocols.InvokeCompletedEventArgs invokeArgs = ((System.Web.Services.Protocols.InvokeCompletedEventArgs)(arg));
                this.findByEmailCompleted(this, new findByEmailCompletedEventArgs(invokeArgs.Results, invokeArgs.Error, invokeArgs.Cancelled, invokeArgs.UserState));
            }
        }
        
        /// <remarks/>
        public new void CancelAsync(object userState) {
            base.CancelAsync(userState);
        }
        
        private bool IsLocalFileSystemWebService(string url) {
            if (((url == null) 
                        || (url == string.Empty))) {
                return false;
            }
            System.Uri wsUri = new System.Uri(url);
            if (((wsUri.Port >= 1024) 
                        && (string.Compare(wsUri.Host, "localHost", System.StringComparison.OrdinalIgnoreCase) == 0))) {
                return true;
            }
            return false;
        }
    }
    
    /// <comentarios/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Xml", "4.6.1064.2")]
    [System.SerializableAttribute()]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.ComponentModel.DesignerCategoryAttribute("code")]
    [System.Xml.Serialization.XmlTypeAttribute(Namespace="http://impl.business.sdi.com/")]
    public partial class user {
        
        private string emailField;
        
        private long idField;
        
        private bool idFieldSpecified;
        
        private string loginField;
        
        private string nameField;
        
        private string passwordField;
        
        private string stateField;
        
        private userStatus statusField;
        
        private bool statusFieldSpecified;
        
        private string surnameField;
        
        /// <comentarios/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
        public string email {
            get {
                return this.emailField;
            }
            set {
                this.emailField = value;
            }
        }
        
        /// <comentarios/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
        public long id {
            get {
                return this.idField;
            }
            set {
                this.idField = value;
            }
        }
        
        /// <comentarios/>
        [System.Xml.Serialization.XmlIgnoreAttribute()]
        public bool idSpecified {
            get {
                return this.idFieldSpecified;
            }
            set {
                this.idFieldSpecified = value;
            }
        }
        
        /// <comentarios/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
        public string login {
            get {
                return this.loginField;
            }
            set {
                this.loginField = value;
            }
        }
        
        /// <comentarios/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
        public string name {
            get {
                return this.nameField;
            }
            set {
                this.nameField = value;
            }
        }
        
        /// <comentarios/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
        public string password {
            get {
                return this.passwordField;
            }
            set {
                this.passwordField = value;
            }
        }
        
        /// <comentarios/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
        public string state {
            get {
                return this.stateField;
            }
            set {
                this.stateField = value;
            }
        }
        
        /// <comentarios/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
        public userStatus status {
            get {
                return this.statusField;
            }
            set {
                this.statusField = value;
            }
        }
        
        /// <comentarios/>
        [System.Xml.Serialization.XmlIgnoreAttribute()]
        public bool statusSpecified {
            get {
                return this.statusFieldSpecified;
            }
            set {
                this.statusFieldSpecified = value;
            }
        }
        
        /// <comentarios/>
        [System.Xml.Serialization.XmlElementAttribute(Form=System.Xml.Schema.XmlSchemaForm.Unqualified)]
        public string surname {
            get {
                return this.surnameField;
            }
            set {
                this.surnameField = value;
            }
        }
    }
    
    /// <comentarios/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Xml", "4.6.1064.2")]
    [System.SerializableAttribute()]
    [System.Xml.Serialization.XmlTypeAttribute(Namespace="http://impl.business.sdi.com/")]
    public enum userStatus {
        
        /// <comentarios/>
        ACTIVE,
        
        /// <comentarios/>
        CANCELLED,
    }
    
    /// <comentarios/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Xml", "4.6.1064.2")]
    [System.SerializableAttribute()]
    [System.Xml.Serialization.XmlTypeAttribute(Namespace="http://impl.business.sdi.com/")]
    public enum seatStatus {
        
        /// <comentarios/>
        ACCEPTED,
        
        /// <comentarios/>
        EXCLUDED,
        
        /// <comentarios/>
        NO_SEAT,
    }
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Web.Services", "4.6.1038.0")]
    public delegate void findByIdCompletedEventHandler(object sender, findByIdCompletedEventArgs e);
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Web.Services", "4.6.1038.0")]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.ComponentModel.DesignerCategoryAttribute("code")]
    public partial class findByIdCompletedEventArgs : System.ComponentModel.AsyncCompletedEventArgs {
        
        private object[] results;
        
        internal findByIdCompletedEventArgs(object[] results, System.Exception exception, bool cancelled, object userState) : 
                base(exception, cancelled, userState) {
            this.results = results;
        }
        
        /// <remarks/>
        public user Result {
            get {
                this.RaiseExceptionIfNecessary();
                return ((user)(this.results[0]));
            }
        }
    }
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Web.Services", "4.6.1038.0")]
    public delegate void findAllCompletedEventHandler(object sender, findAllCompletedEventArgs e);
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Web.Services", "4.6.1038.0")]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.ComponentModel.DesignerCategoryAttribute("code")]
    public partial class findAllCompletedEventArgs : System.ComponentModel.AsyncCompletedEventArgs {
        
        private object[] results;
        
        internal findAllCompletedEventArgs(object[] results, System.Exception exception, bool cancelled, object userState) : 
                base(exception, cancelled, userState) {
            this.results = results;
        }
        
        /// <remarks/>
        public user[] Result {
            get {
                this.RaiseExceptionIfNecessary();
                return ((user[])(this.results[0]));
            }
        }
    }
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Web.Services", "4.6.1038.0")]
    public delegate void findAllActiveCompletedEventHandler(object sender, findAllActiveCompletedEventArgs e);
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Web.Services", "4.6.1038.0")]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.ComponentModel.DesignerCategoryAttribute("code")]
    public partial class findAllActiveCompletedEventArgs : System.ComponentModel.AsyncCompletedEventArgs {
        
        private object[] results;
        
        internal findAllActiveCompletedEventArgs(object[] results, System.Exception exception, bool cancelled, object userState) : 
                base(exception, cancelled, userState) {
            this.results = results;
        }
        
        /// <remarks/>
        public user[] Result {
            get {
                this.RaiseExceptionIfNecessary();
                return ((user[])(this.results[0]));
            }
        }
    }
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Web.Services", "4.6.1038.0")]
    public delegate void cancelUserCompletedEventHandler(object sender, System.ComponentModel.AsyncCompletedEventArgs e);
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Web.Services", "4.6.1038.0")]
    public delegate void findByLoginCompletedEventHandler(object sender, findByLoginCompletedEventArgs e);
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Web.Services", "4.6.1038.0")]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.ComponentModel.DesignerCategoryAttribute("code")]
    public partial class findByLoginCompletedEventArgs : System.ComponentModel.AsyncCompletedEventArgs {
        
        private object[] results;
        
        internal findByLoginCompletedEventArgs(object[] results, System.Exception exception, bool cancelled, object userState) : 
                base(exception, cancelled, userState) {
            this.results = results;
        }
        
        /// <remarks/>
        public user Result {
            get {
                this.RaiseExceptionIfNecessary();
                return ((user)(this.results[0]));
            }
        }
    }
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Web.Services", "4.6.1038.0")]
    public delegate void listByAcceptedAndPendingCompletedEventHandler(object sender, listByAcceptedAndPendingCompletedEventArgs e);
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Web.Services", "4.6.1038.0")]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.ComponentModel.DesignerCategoryAttribute("code")]
    public partial class listByAcceptedAndPendingCompletedEventArgs : System.ComponentModel.AsyncCompletedEventArgs {
        
        private object[] results;
        
        internal listByAcceptedAndPendingCompletedEventArgs(object[] results, System.Exception exception, bool cancelled, object userState) : 
                base(exception, cancelled, userState) {
            this.results = results;
        }
        
        /// <remarks/>
        public user[] Result {
            get {
                this.RaiseExceptionIfNecessary();
                return ((user[])(this.results[0]));
            }
        }
    }
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Web.Services", "4.6.1038.0")]
    public delegate void registerCompletedEventHandler(object sender, registerCompletedEventArgs e);
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Web.Services", "4.6.1038.0")]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.ComponentModel.DesignerCategoryAttribute("code")]
    public partial class registerCompletedEventArgs : System.ComponentModel.AsyncCompletedEventArgs {
        
        private object[] results;
        
        internal registerCompletedEventArgs(object[] results, System.Exception exception, bool cancelled, object userState) : 
                base(exception, cancelled, userState) {
            this.results = results;
        }
        
        /// <remarks/>
        public user Result {
            get {
                this.RaiseExceptionIfNecessary();
                return ((user)(this.results[0]));
            }
        }
    }
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Web.Services", "4.6.1038.0")]
    public delegate void listByTripAndSeatStatusCompletedEventHandler(object sender, listByTripAndSeatStatusCompletedEventArgs e);
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Web.Services", "4.6.1038.0")]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.ComponentModel.DesignerCategoryAttribute("code")]
    public partial class listByTripAndSeatStatusCompletedEventArgs : System.ComponentModel.AsyncCompletedEventArgs {
        
        private object[] results;
        
        internal listByTripAndSeatStatusCompletedEventArgs(object[] results, System.Exception exception, bool cancelled, object userState) : 
                base(exception, cancelled, userState) {
            this.results = results;
        }
        
        /// <remarks/>
        public user[] Result {
            get {
                this.RaiseExceptionIfNecessary();
                return ((user[])(this.results[0]));
            }
        }
    }
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Web.Services", "4.6.1038.0")]
    public delegate void findByEmailCompletedEventHandler(object sender, findByEmailCompletedEventArgs e);
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Web.Services", "4.6.1038.0")]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.ComponentModel.DesignerCategoryAttribute("code")]
    public partial class findByEmailCompletedEventArgs : System.ComponentModel.AsyncCompletedEventArgs {
        
        private object[] results;
        
        internal findByEmailCompletedEventArgs(object[] results, System.Exception exception, bool cancelled, object userState) : 
                base(exception, cancelled, userState) {
            this.results = results;
        }
        
        /// <remarks/>
        public user Result {
            get {
                this.RaiseExceptionIfNecessary();
                return ((user)(this.results[0]));
            }
        }
    }
}

#pragma warning restore 1591