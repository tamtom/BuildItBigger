package com.itdeveapps.omar.builditbigger;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.itdeveapps.omar.builditbigger.joker.Joker;

/** An endpoint class we are exposing */
@Api(
        name = "jokerApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.builditbigger.omar.com",
                ownerName = "backend.builditbigger.omar.com",
                packagePath = ""
        )
)
public class JokerApi {
    private Joker joker = new Joker();

    @ApiMethod(name = "tellAJoke")
    public Joke tellAJoke() {
        Joke response = new Joke();
        response.setData(joker.tellTheJoke());
        return response;
    }
}
