package id.fabiworld.acaraku.model.enumvalue;

public enum TransactionStatus {
    ORDERED("ORDERED"),
    PAID("PAID"),
    CONFIRMED("CONFIRMED"),
    CANCELED("CANCELED");

    String status;

    public static TransactionStatus parse(String type){
        for(TransactionStatus status: TransactionStatus.values()){
            if(status.name().equals(type)){
                return status;
            }
        }

        return null;
    }

    TransactionStatus(String status){
        this.status = status;
    }


    @Override
    public String toString() {
        return status;
    }
}
