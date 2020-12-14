package com.celerapps.celermail.shared.models;

import androidx.fragment.app.Fragment;

/**
 * Esta clase sirve para definir y asociar un fragmento a un tab en el viewpager2
 *
 * @author: Álvaro Reina Carrizosa
 */
public class FragmentDescriptor {

    private Fragment fragment;
    private String title;
    //private Icon icon;

    /**
     *
     * @param fragment el fragmento que se va a inflar
     * @param title el título del tab
     */
    public FragmentDescriptor(Fragment fragment, String title) {
        this.fragment = fragment;
        this.title = title;
        //this.icon = icon;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
/*
    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }
    */

}
