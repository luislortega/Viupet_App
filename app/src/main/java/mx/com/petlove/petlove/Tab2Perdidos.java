package mx.com.petlove.petlove;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by Luis Gerardo Leon Ortega on 18/04/2017.
 */

public class Tab2Perdidos extends Fragment{

    ListView menu;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab2perdidos, container, false);

        MenuInicio MenuEditadoDatos[] = new MenuInicio[]{

                new MenuInicio(R.drawable.iconopet,"Firulais", "$1500"),

                new MenuInicio(R.drawable.iconopet,"Hercules", "$2200"),

                new MenuInicio(R.drawable.iconopet,"Bobi", "$1500"),

                new MenuInicio(R.drawable.iconopet,"Gatito", "$900"),

                new MenuInicio(R.drawable.iconopet,"Skeki", "$600"),

                new MenuInicio(R.drawable.iconopet,"Tita", "$255"),

                new MenuInicio(R.drawable.iconopet,"Tita", "$255"),

                new MenuInicio(R.drawable.iconopet,"Tita", "$255"),

                new MenuInicio(R.drawable.iconopet,"Tita", "$255"),

                new MenuInicio(R.drawable.iconopet,"Tita", "$255"),

        };

        MenuInicioAdapter adaptadorMenu = new MenuInicioAdapter(container.getContext(), R.layout.listview_item_row, MenuEditadoDatos);

        menu = (ListView) rootView.findViewById(R.id.listMenu);

        menu.setAdapter(adaptadorMenu);

        return rootView;
    }
}
