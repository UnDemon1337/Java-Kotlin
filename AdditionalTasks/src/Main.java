import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите номер задачи котоырй хотите проверить: ");
            int number = scanner.nextInt();
            switch (number) {
                case (1):
                    PopulationGrowth();
                    break;
                case (2):
                    DynamicPopulationGrowth();
                    break;
                case (3):
                    BankDeposit();
                    break;
                default:
                    break;
            }

        }
    }
    public static void PopulationGrowth() {
        double population = 10000000; // начальное население
        double birthRate = 14.0 / 1000; // рождаемость на 1000 человек
        double deathRate = 8.0 / 1000;  // смертность на 1000 человек

        for (int i = 0; i < 10; i++) {
            double births = population * birthRate;
            double deaths = population * deathRate;
            population += births - deaths; // новое население
        }

        System.out.printf("Население через 10 лет составляет: " + population + "\n");
    }
    public static void DynamicPopulationGrowth() {
        double population = 10000000; // начальное население
        double birthRate = 14.0 / 1000; // рождаемость на 1000 человек
        double deathRate = 8.0 / 1000;  // смертность на 1000 человек

        for (int i = 0; i < 10; i++) {
            double births = population * birthRate;
            double deaths = population * deathRate;
            population += births - deaths; // новое население
            birthRate = Math.max((birthRate * 1000 - 1) / 1000, 7.0 / 1000);
            deathRate = Math.max((deathRate * 1000 - 1) / 1000, 6.0 / 1000);
        }

        System.out.printf("Население через 10 лет составляет: " + population + "\n");
    }
    public static void BankDeposit() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите сумму вклада: "); float deposit = scanner.nextFloat();
        System.out.print("Введите количество месяцев: "); int months = scanner.nextInt();

        for (int i = 0; i < months; i++) {
            deposit += deposit * 0.07; // 7% начисление на вклад
        }

        System.out.printf("Конечная сумма вклада через " + months + " месяцев:" +  deposit + "\n");
    }
}