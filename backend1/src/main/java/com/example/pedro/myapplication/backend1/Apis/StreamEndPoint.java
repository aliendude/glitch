package com.example.pedro.myapplication.backend1.Apis;

import com.example.pedro.myapplication.backend1.Model.Stream;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import java.util.List;
import java.util.logging.Logger;

import static com.example.pedro.myapplication.backend1.OfyService.ofy;

/**
 * Created by pedro on 23/07/15.
 */
@Api(name = "streams", version = "v1", namespace = @ApiNamespace(ownerDomain = "backend1.myapplication.pedro.example.com", ownerName = "backend1.myapplication.pedro.example.com", packagePath = ""))
public class StreamEndPoint {

    private static final Logger log = Logger.getLogger(StreamEndPoint.class.getName());

    @ApiMethod(name = "addStream")
    public void addStream( Stream stream) {
        ofy().save().entity(stream).now();
    }
    @SuppressWarnings({"cast", "unchecked"})
    @ApiMethod(name = "getStreams")
    public List<Stream> getAllStreams()
    {
        return ofy().load().type(Stream.class).list();
    }
}
