package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);


        Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Jordan");
        owner1.setAddress("123 Bickerel Ave");
        owner1.setCity("Miami");
        owner1.setTelephone("555-555-5555");


        Pet mikesPet = new Pet();
        mikesPet.setPetType(savedDogPetType);
        mikesPet.setOwner(owner1);
        mikesPet.setBirthDate(LocalDate.now());
        mikesPet.setName("Rosco");

        owner1.getPets().add(mikesPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Tiger");
        owner2.setLastName("Woods");
        owner2.setAddress("123 Woods Ave");
        owner2.setCity("Sarasota");
        owner2.setTelephone("444-555-4444");

        Pet tigersPet = new Pet();
        tigersPet.setPetType(savedCatPetType);
        tigersPet.setOwner(owner2);
        tigersPet.setBirthDate(LocalDate.now());
        tigersPet.setName("Cat");

        owner2.getPets().add(tigersPet);

        ownerService.save(owner2);

        System.out.println("Loaded Owners.... ");

        Vet vet1 = new Vet();
        vet1.setFirstName("Tony");
        vet1.setLastName("Snow");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Tony");
        vet2.setLastName("Snow");

        vetService.save(vet2);

        System.out.println("Loaded Vets... ");
    }
}
