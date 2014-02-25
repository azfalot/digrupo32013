package logica;

import datos.conexionbd.POJOS.Usuario;
import datos.config.Config;
import datos.strings.Language;
import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
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

    private static final Methods instance = new Methods();
    private Config cfg;
    private Language lang;
    private final Queries q = new Queries();
    private Usuario user = new Usuario();

    /*
     * CONSTRUCTOR
     */
    private Methods() {
        getConfig();//configura los valores iniciales
    }

    public static Methods getInstance() {
        return instance;
    }

    /*
     * CALCULO DE RENDIMIENTO
     */
    public double calculaRendimiento(Date fechaInicial, Date fechaFinal) {
        if(fechaFinal.getTime()<fechaInicial.getTime()){
            //si la fecha final es anterior retorna 0
            return 0.0;
        }
        if (fechaFinal.getTime() > new Date().getTime()) {
            /*
            * para ir calculado el rendimiento dia a dia se tendra en cuenta que
            * si estamos en medio del periodo a fin de cuentas no se contaran los
            * dias que aun no han llegado.
            */
            fechaFinal = new Date();
        }

        Calendar cini = Calendar.getInstance();
        Calendar cfin = Calendar.getInstance();

        cini.setTime(fechaInicial);
        cfin.setTime(fechaFinal);

        ArrayList<Semana> semanas = new ArrayList();

        int semanaInicio = cini.get(Calendar.WEEK_OF_YEAR);
        int semanaFin = cfin.get(Calendar.WEEK_OF_YEAR);
        int ultima = semanaFin;

        //primero se calcula el rendimiento durante los años completos
        int dif = cfin.get(Calendar.YEAR) - cini.get(Calendar.YEAR);
        if (dif > 1) {
            for (int i = 1; i < dif; i++) {
                int year = cini.get(Calendar.YEAR) + i;
                for (int semana = 1; semana <= 52; semana++) {
                    Calendar dia1 = Calendar.getInstance();
                    Calendar dia7 = Calendar.getInstance();
                    dia1.set(Calendar.YEAR, year);
                    dia1.set(Calendar.WEEK_OF_YEAR, semana);
                    dia1.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                    dia7.set(Calendar.YEAR, year);
                    dia7.set(Calendar.WEEK_OF_YEAR, semana);
                    dia7.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
                    Semana s = new Semana();
                    s.addHoras(q.getHorasEntrenamiento(getUserId(), dia1.getTime(), dia7.getTime()));
                    s.addItinerarios(q.getCantidadItinerarios(getUserId(), dia1.getTime(), dia7.getTime()));
                    semanas.add(s);
                }
            }
        }
        //despues el del año final
        if (cfin.get(Calendar.YEAR) != cini.get(Calendar.YEAR)) {
            int year = cfin.get(Calendar.YEAR);
            for (int semana = 1; semana <= semanaFin; semana++) {
                Calendar dia1 = Calendar.getInstance();
                Calendar dia7 = Calendar.getInstance();
                dia1.set(Calendar.YEAR, year);
                dia1.set(Calendar.WEEK_OF_YEAR, semana);
                dia1.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                dia7.set(Calendar.YEAR, year);
                dia7.set(Calendar.WEEK_OF_YEAR, semana);
                dia7.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
                Semana s = new Semana();
                if (semana != semanaFin) {
                    s.addHoras(q.getHorasEntrenamiento(getUserId(), dia1.getTime(), dia7.getTime()));
                    s.addItinerarios(q.getCantidadItinerarios(getUserId(), dia1.getTime(), dia7.getTime()));
                } else {
                    s.addHoras(q.getHorasEntrenamiento(getUserId(), dia1.getTime(), getPeriodoFin()));
                    s.addItinerarios(q.getCantidadItinerarios(getUserId(), dia1.getTime(), getPeriodoFin()));
                }
                semanas.add(s);
            }
        }
        //y despues el del año inicial
        int year = cini.get(Calendar.YEAR);
        for (int semana = semanaInicio; semana <= ultima; semana++) {
            Calendar dia1 = Calendar.getInstance();
            Calendar dia7 = Calendar.getInstance();
            dia1.set(Calendar.YEAR, year);
            dia1.set(Calendar.WEEK_OF_YEAR, semana);
            dia1.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            dia7.set(Calendar.YEAR, year);
            dia7.set(Calendar.WEEK_OF_YEAR, semana);
            dia7.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
            Semana s = new Semana();
            if (semana != semanaInicio) {
                s.addHoras(q.getHorasEntrenamiento(getUserId(), dia1.getTime(), dia7.getTime()));
                s.addItinerarios(q.getCantidadItinerarios(getUserId(), dia1.getTime(), dia7.getTime()));
            } else {
                s.addHoras(q.getHorasEntrenamiento(getUserId(), getPeriodoInicio(), dia7.getTime()));
                s.addItinerarios(q.getCantidadItinerarios(getUserId(), getPeriodoInicio(), dia7.getTime()));
            }
            semanas.add(s);
        }

        //ahora se calculan las medias
        double horasMedias = 0;
        double itinerariosMedios = 0;
        for (Semana s : semanas) {
            horasMedias += s.getHorasEnt();
            itinerariosMedios += s.getItinerarios();
        }
        horasMedias = horasMedias / semanas.size();
        itinerariosMedios = itinerariosMedios / semanas.size();

        double rendimiento = 0;
        if (horasMedias >= 10) {
            rendimiento += 5;
        } else {
            rendimiento += 0.5 * (int)horasMedias;
        }
        if (itinerariosMedios >= 20) {
            rendimiento += 5;
        } else {
            rendimiento += 0.25 * (int)itinerariosMedios;
        }

        return rendimiento;
    }

    public double calculaRendimiento() {
        //este metodo sera el llamado
        //calcula el rendimiento tomando las fechas del periodo indicado
        return calculaRendimiento(getPeriodoInicio(), getPeriodoFin());
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

    public Date getPeriodoInicio() {
        return user.getPeriodoInicio();
    }

    public Date getPeriodoFin() {
        return user.getPeriodoFin();
    }

    public void setPeriodoInicio(Date periodoInicio) {
        user.setPeriodoInicio(periodoInicio);
    }

    public void setPeriodoFin(Date PeriodoFin) {
        user.setPeriodoFin(PeriodoFin);
    }

    public void setUserName(String nombre) {
        user.setNombre(nombre);
    }

    public void setUserWallpaper(File wallpaper) {
        user.setWallpaper(q.copyPicture(wallpaper, "images" + File.separator + "wp-" + getUserId()).getAbsolutePath());
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

    public void setUserDefault(boolean makeDefault) {
        if (makeDefault) {
            cfg.setDefaultUser(user.getId());
        }
    }

    public void setLanguage(String language) {
        cfg.setLanguage(language);
    }

    public String getLanguage() {
        return cfg.getLanguage();
    }

    public boolean isUserDefault() {
        return cfg.getDefaultUser() == getUserId();
    }

    public int getDefaultUser() {
        return cfg.getDefaultUser();
    }

    public void setDefaultUser(int user) {
        cfg.setDefaultUser(user);
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
    
    public Connection getConnection(){
        return q.getBd().getConnection();
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

    public ArrayList getEntrenamientos() {
        return q.getEntrenamientos(getUserId());
    }

    public ArrayList getItinerarios() {
        return q.getItinerarios(getUserId());
    }

    public void deleteEntrenamiento(int p_sesion_entrenamientos) {
        q.deleteEntrenamiento(p_sesion_entrenamientos);
    }

    public void deleteItinerario(int p_itinerario) {
        q.deleteItinerario(p_itinerario);
    }

    public void deleteUsuario(int p_escaladores) {
        q.deleteUsuario(p_escaladores);
    }

    public ArrayList<Usuario> getUsuarios() {
        return q.getUsuarios();
    }

}
