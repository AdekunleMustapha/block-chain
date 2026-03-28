import modules.blockchain.application.usecases.CreateBlockUseCase;

public class Main {
    public static void main(String[] args) {
        System.out.println("Creating genesis block");
        String blockHash1 = CreateBlockUseCase.execute(
                null,
                "Akin credits $10000 to Akin"
        );
        System.out.println("Creating second block");
        String blockHash2 = CreateBlockUseCase.execute(
                blockHash1,
                "Akin sends $100 to Meli",
                "Akin sends $9900 to Akin"
        );
        System.out.println("Creating third block");
        CreateBlockUseCase.execute(
                blockHash2,
                "Akin sends $900 to Metro",
                "Akin sends $900 to Quam",
                "Akin sends $100 to Akin"
        );
    }
}
