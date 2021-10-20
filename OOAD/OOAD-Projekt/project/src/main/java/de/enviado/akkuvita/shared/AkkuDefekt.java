package de.enviado.akkuvita.shared;

public enum AkkuDefekt {
    NOT_CHARGING("Does not charge",1.0f),
    CHARGING_SOCKET_DEFECTIVE("Chargin socket defective/loose",2),
    SPORADIC_ERROR_CHARGIN("sporadic error (Charging)",3.1f),
    SPORADIC_ERROR_BIKE("sporadic error (Bike)",3.2f);
    //TODO: Add remaining status cases


    public final String description;
    public final float code;

    AkkuDefekt(String description, float code){
        this.description = description;
        this.code = code;
    }

    public AkkuDefekt fromCode(float code){
        for( AkkuDefekt status : AkkuDefekt.values()){
            if(status.code == code){
                return status;
            }
        }
        return null;
    }
}
