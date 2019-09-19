package com.securitypro.proapp.Model;

/**
 * Created by Chetna on 10-Mar-18.
 */
import android.graphics.drawable.Drawable;

public class Application {
    public Drawable _icon;
    public String _name;
    public String _package;

    public Drawable get_icon() {
        return _icon;
    }

    public void set_icon(Drawable _icon) {
        this._icon = _icon;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_package() {
        return _package;
    }

    public void set_package(String _package) {
        this._package = _package;
    }

    public Application(String _name, String _package, Drawable _icon) {
        this._name = _name;
        this._package = _package;
        this._icon = _icon;
    }

}
