    package logica;

import datos.conexionbd.POJOS.Usuario;
import datos.config.Config;
import datos.strings.Language;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Grupo 3
 */
public class Methods {
    /*
     * Clase que conecta datos con interfaz. Se ha programado con un patron singleton de diseño.
     */

    private static final Methods instance= new Methods();;
    private Config cfg;
    private Language lang;
    private final Queries q = new Queries();
    private Usuario user = new Usuario();
    
    private Date fechaRendimiento;//fecha para calcular el rendimiento
    
    /*
    * CONSTRUCTOR
    */
    private Methods() {
        getConfig();//configura los valores iniciales
        /*
        * Se define la fecha para la cual se calculara el rendimiento:
        * Ya que el rendimiento es una cifra que abarca una semana entera
        * se tomará desde hace 6 días.
        */
        fechaRendimiento=new Date((long)(new Date().getTime()-(86400*6000)));
        
    }

    public static Methods getInstance() {
        return instance;
    }
    
    /*
     * CALCULO DE RENDIMIENTO
     */
    public Date getFechaRendimiento() {
        return fechaRendimiento;
    }

    public void setFechaRendimiento(Date fechaRendimiento) {
        this.fechaRendimiento = fechaRendimiento;
    }
    
    public double calculaRendimiento(Date fechaInicial, Date fechaFinal){
        double rendimiento=0;
        double horasEntrenamiento=q.getHorasEntrenamiento(getUserId(), fechaInicial, fechaFinal);
        int itinerarios=q.getCantidadItinerarios(getUserId(), fechaInicial, fechaFinal);
        if(horasEntrenamiento>=10){
            rendimiento+=5;
        }else{
            rendimiento+=horasEntrenamiento*0.5;
        }
        if(itinerarios>=20){
            rendimiento+=5;
        }else{
            rendimiento+=itinerarios*0.25;
        }
        return rendimiento;
    }
    public double calculaRendimiento(){
        return calculaRendimiento(fechaRendimiento,new Date());
    }
    
    /*
    * GETTERS Y SETTERS PARA EL USUARIO
    */
    public int getUserId() {
        return user.getId();
    }

    public String getWallpaper() {
        return user.getWallpaper();
    }

    public String getUserName() {
        return user.getNombre();
    }
    public Date getPeriodoInicio(){
        return user.getPeriodoInicio();
    }
    public Date getPeriodoFin(){
        return user.getPeriodoFin();
    }
    
    public void setPeriodoInicio(Date periodoInicio){
        user.setPeriodoInicio(periodoInicio);
    }
    public void setPeriodoFin(Date PeriodoFin){
        user.setPeriodoFin(PeriodoFin);
    }
    
    public void setUserName(String nombre){
        user.setNombre(nombre);
    }
    
    public void setUserWallpaper(File wallpaper){
        user.setWallpaper(q.copyPicture(wallpaper,"images" + File.separator+"wp-"+ getUserId()).getAbsolutePath());
    }

    /*
    * OPERACIONES CON LA CONFIGURACION config.cfg
    */
    public void setUser(Usuario user, boolean makeDefault) {
        /*
         * Permite seleccionar un usuario y ponerlo como por defecto o no
         */
        this.user = user;
        if (makeDefault) {
            cfg.setDefaultUser(user.getId());
        } else {
            cfg.setDefaultUser(0);
        }
    }
    
    public void setUserDefault(boolean makeDefault){
        if (makeDefault) {
            cfg.setDefaultUser(user.getId());
        }
    }
    
    public void setLanguage(String language){
        cfg.setLanguage(language);
    }
    
    public String getLanguage(){
        return cfg.getLanguage();
    }
    
    public boolean isUserDefault(){
        return cfg.getDefaultUser()==getUserId();
    }
    
    private void getConfig() {
        /*
         * Obtener configuración del archivo config.cfg, se ejecutara al principio
         */
        cfg = new Config();
        lang = Language.getInstance(cfg.getLanguage());
        if (cfg.getDefaultUser() != 0) {
            this.user = getUsuario(cfg.getDefaultUser());
        }
    }
    
    
    /*
    * UTILIDADES
    */

    public String write(String text) {
        /*
         * Metodo que conecta la interfaz con los archivos de idioma
         */
        return lang.write(text);
    }
    
    public ObservableList getDificultades() {
        //lista de dificultades
        ObservableList data = FXCollections.observableArrayList();
        data.add("1");
        data.add("2");
        data.add("3");
        data.add("4");
        data.add("5a");
        data.add("5b");
        data.add("5c");
        data.add("6a");
        data.add("6a+");
        data.add("6b");
        data.add("6b+");
        data.add("6c");
        data.add("6c+");
        data.add("7a");
        data.add("7a+");
        data.add("7b");
        data.add("7b+");
        data.add("7c");
        data.add("7c+");
        data.add("8a");
        data.add("8a+");
        data.add("8b");
        data.add("8b+");
        data.add("8c");
        data.add("8c+");
        data.add("9a");
        data.add("9a+");
        data.add("9b");
        data.add("9b+");
        return data;
    }

    /*
     * CONSULTAS
     *
     * Las consultas estan en la clase Queries, esta clase solo actua como comunicador.
     * Esta forma de trabajar es por cuestion de orden y para que cuando una clase 
     * de POJOS quiera hacer un update no necesite la instacia de Methods y pueda hacerse
     * su propia instancia de Queries.
     */
    public Usuario getUsuario(int id) {
        return q.getUsuario(id);
    }

    public Usuario getUserFromUsername(String name) {
        return q.getUserFromUsername(name);
    }

    public ArrayList getNombreUsuarios() {
        return q.getNombreUsuarios();
    }

    public int altaUsuario(String nombre) {
        return q.altaUsuario(nombre);
    }

    public void altaEntrenamiento(String horaIni, String minIni, String horaFin, String minFin, Date fechaSesion, int tipo, String descripcion) {
        q.altaEntrenamiento(getUserId(), horaIni, minIni, horaFin, minFin, fechaSesion, tipo, descripcion);
    }

    public void modificarRegistro(String tabla, int id, String campo, String nuevoValor) {
        q.modificarRegistro(tabla, id, campo, nuevoValor);
    }

    public ObservableList<String> getLocalizaciones() {
        return q.getLocalizaciones(getUserId());
    }

    public void altaItinerario(String nombre, String dificultad, String localizacion, int tipo, String foto, Date fecha) {
        q.altaItinerario(nombre, dificultad, localizacion, tipo, new File(foto), fecha, getUserId());
    }
    
    public ArrayList getEntrenamientos(){
        return q.getEntrenamientos(getUserId());
    }
    
    public ArrayList getItinerarios(){
        return q.getItinerarios(getUserId());
    }
    
    public void deleteEntrenamiento(int p_sesion_entrenamientos){
        q.deleteEntrenamiento(p_sesion_entrenamientos);
    }
    
    public void deleteItinerario(int p_itinerario){
        q.deleteItinerario(p_itinerario);
    }
    
    public void deleteUsuario(int p_escaladores){
        q.deleteUsuario(p_escaladores);
    }
    
    public ArrayList<Usuario> getUsuarios(){
        return q.getUsuarios();
    }
    
}
