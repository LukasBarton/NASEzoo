package Zoo;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;


@Path("AnimalList")
@Produces(MediaType.APPLICATION_JSON)
public class AnimalList{

    private static ArrayList<String> animals
            = new ArrayList<>();

    @GET
    public Response getAnimals(
            @DefaultValue("") @QueryParam("startsWith") String startsWith
    ) {
        ArrayList<String> filteredOutAnimals = new ArrayList<>();

        for (int i = 0; i < animals.size(); i++) {
            if(animals.get(i).startsWith(startsWith))
                filteredOutAnimals.add(animals.get(i));
        }

        return Response.ok(filteredOutAnimals).build();
    }

    @GET
    @Path("{id}")
    public Response getDetail(
            @PathParam("id") int AnimalIndex
    ) {
        String foundAnimal = animals.get(AnimalIndex);
        return Response.ok(foundAnimal).build();
    }

    @POST
    public Response addAnimal(@FormParam("animal") String newAnimal) {
        if (newAnimal == null)
            return Response.status(Response.Status.BAD_REQUEST).build();

        animals.add(newAnimal);
        return Response.ok().build();
    }
    @DELETE
    public Response delAnimal(@FormParam("animal") String deadAnimal) {
        if (deadAnimal == null)
            return Response.status(Response.Status.BAD_REQUEST).build();

        animals.remove(deadAnimal);
        return Response.ok().build();
    }


}
