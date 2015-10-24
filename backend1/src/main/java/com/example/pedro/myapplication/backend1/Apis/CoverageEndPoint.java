package com.example.pedro.myapplication.backend1.Apis;

import com.example.pedro.myapplication.backend1.Model.Coverage;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import java.util.List;
import java.util.logging.Logger;

import static com.example.pedro.myapplication.backend1.OfyService.ofy;

/**
 * Created by pedro on 23/07/15.
 */
@Api(name = "coverages", version = "v1", namespace = @ApiNamespace(ownerDomain = "backend1.myapplication.pedro.example.com", ownerName = "backend1.myapplication.pedro.example.com", packagePath = ""))
public class CoverageEndPoint {

    private static final Logger log = Logger.getLogger(CoverageEndPoint.class.getName());

    @ApiMethod(name = "addCoverage")
    public void addCoverage( Coverage coverage) {
        ofy().save().entity(coverage).now();
    }
    @SuppressWarnings({"cast", "unchecked"})
    @ApiMethod(name = "getCoverages")
    public List<Coverage> getMapMarkers()
    {
        return ofy().load().type(Coverage.class).list();
    }
}
