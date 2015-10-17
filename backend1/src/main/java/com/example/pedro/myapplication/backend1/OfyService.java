package com.example.pedro.myapplication.backend1;


import com.example.pedro.myapplication.backend1.Model.Coverage;
import com.example.pedro.myapplication.backend1.Model.Stream;
import com.example.pedro.myapplication.backend1.Model.User;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;

/**
 * Objectify service wrapper so we can statically register our persistence classes
 * More on Objectify here : https://code.google.com/p/objectify-appengine/
 */
public class OfyService {

    static {
        ObjectifyService.register(Coverage.class);
        ObjectifyService.register(User.class);
        ObjectifyService.register(Stream.class);
    }

    public static Objectify ofy() {
        return ObjectifyService.ofy();
    }

    public static ObjectifyFactory factory() {
        return ObjectifyService.factory();
    }
}
