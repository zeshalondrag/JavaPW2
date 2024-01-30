import java.util.Scanner;

public class JavaPW2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        byte degree = 2;

        System.out.println("Калькулятор здоровья с рекомендациями и калориями");
        System.out.println("-------------------------------------------------");

        System.out.print("Введите возраст: ");
        byte age = sc.nextByte();

        System.out.print("Введите массу тела в (кг): ");
        byte weight = sc.nextByte();

        System.out.print("Введите рост в (метрах (Пример - 1,65)): ");
        double height = sc.nextDouble();

        System.out.println("-------------------------------------------------");
        System.out.println("Возраст: " + age);

        double bmi = weight / Math.pow(height, degree); // Расчёт ИМТ
        System.out.println("ИМТ: " + bmi);
        double ideal_weight = 24.9 * Math.pow(height, degree); // Расчёт идеального веса
        System.out.println("-------------------------------------------------");

        if (bmi > 24.9) {
            double weight_loss_target = weight - ideal_weight;
            double daily_caloric_intake = calculateDailyCaloricIntake(weight, age, ideal_weight, bmi, "weight_loss");
            System.out.println("Рекомендуется снизить вес для достижения идеального веса.\nИдеальный вес: " + ideal_weight +
                    " кг.\nНеобходимо сбросить: " + weight_loss_target + " кг.\nРекомендуемый дневной прием калорий: " + daily_caloric_intake);
        } else if (bmi < 18.5) {
            double weight_gain_target = ideal_weight - weight;
            double daily_caloric_intake = calculateDailyCaloricIntake(weight, age, ideal_weight, bmi, "weight_gain");
            System.out.println("Рекомендуется увеличить потребление пищи для набора веса.\nИдеальный вес: " + ideal_weight +
                    " кг.\nНеобходимо набрать: " + weight_gain_target + " кг.\nРекомендуемый дневной прием калорий: " + daily_caloric_intake);
        } else {
            double daily_caloric_intake = calculateDailyCaloricIntake(weight, age, ideal_weight, bmi, "maintenance");
            System.out.println("Ваш вес находится в пределах нормы.\nИдеальный вес: " + ideal_weight +
                    ".\nРекомендуемый дневной прием калорий: " + daily_caloric_intake);
        }
    }

    private static double calculateDailyCaloricIntake(double weight, byte age, double ideal_weight, double bmi, String goal) {
        double bmr = 655 + (9.6 * weight) + (1.8 * 160) - (4.7 * age);

        if ("weight_loss".equals(goal)) {
            double weight_loss_target = weight - ideal_weight;
            return bmr - 500;
        } else if ("weight_gain".equals(goal)) {
            double weight_gain_target = ideal_weight - weight;
            return bmr + 500;
        } else {
            return bmr;
        }
    }
}