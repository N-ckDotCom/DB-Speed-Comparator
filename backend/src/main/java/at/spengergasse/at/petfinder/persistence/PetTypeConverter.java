package at.spengergasse.at.petfinder.persistence;


import at.spengergasse.at.petfinder.domain.Pet;
import at.spengergasse.at.petfinder.domain.PetType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * Converts between the Userrole enum and its representation in the database (as a String).
 */
@Converter(autoApply = true)
public class PetTypeConverter implements AttributeConverter<PetType, String> {

    /**
     * Converts a Userrole enum to its database representation.
     * @param PetType The Userrole enum to be converted.
     * @return The database representation of the Userrole enum.
     */
    @Override
    public String convertToDatabaseColumn(PetType petType) {
        if(petType == null) return null;

        return switch(petType){
            case CAT -> "CAT";
            case DOG -> "DOG";
            case BIRD -> "BIRD";
            case FISH -> "FISH";
            case SNAKE -> "SNAKE";
            case TURTLE -> "TURTLE";
        };
    }

    /**
     * Converts a database representation (String) to the corresponding Userrole enum.
     * @param dbValue The database representation of the Userrole enum.
     * @return The Userrole enum corresponding to the database representation.
     * @throws IllegalArgumentException If the database representation is unknown.
     */
    @Override
    public PetType convertToEntityAttribute(String dbValue) {
        if(dbValue == null) return null;
        return switch(dbValue){
            case "CAT" -> PetType.CAT;
            case "DOG" -> PetType.DOG;
            case "BIRD" -> PetType.BIRD;
            case "FISH" -> PetType.FISH;
            case "SNAKE" -> PetType.SNAKE;
            case "TURTLE" ->PetType.TURTLE;

            default -> throw new IllegalArgumentException(dbValue+" is unkown for PetType!");
        };
    }
}
