package mx.com.petlove.petlove;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Luis Gerardo Leon ortega on 19/04/2017.
 */

public class MenuInicioAdapter  extends ArrayAdapter<MenuInicio> {

    Context contexto;
    int layoutResourceID;
    MenuInicio datos[] = null;

    public MenuInicioAdapter(Context contexto, int layoutResourceID, MenuInicio[] datos){
        super(contexto, layoutResourceID, datos);
        this.contexto = contexto;
        this.layoutResourceID = layoutResourceID;
        this.datos = datos;
    }

    public View getView(int posicion, View convertirVista, ViewGroup parent){
        View columna = convertirVista;
        MenuInicioHolder holder = null;

        if (columna == null){
            LayoutInflater inflador = ((Activity)contexto).getLayoutInflater();
            columna = inflador.inflate(layoutResourceID,parent,false);

            holder = new MenuInicioHolder();
            holder.imagen = (ImageView) columna.findViewById(R.id.imagenMenuPerd);
            holder.texto = (TextView) columna.findViewById(R.id.textoMenuPerd);
            holder.recompenza = (TextView) columna.findViewById(R.id.textoRecompenzaPerd);

            columna.setTag(holder);
        }else{
            holder = (MenuInicioHolder)columna.getTag();
        }

        MenuInicio menu = datos[posicion];
        holder.texto.setText(menu.titulo);
        holder.imagen.setImageResource(menu.icono);
        holder.recompenza.setText(menu.recompenza);

        return columna;
    }

    static class MenuInicioHolder{
        ImageView imagen;
        TextView texto, recompenza;
    }
}
