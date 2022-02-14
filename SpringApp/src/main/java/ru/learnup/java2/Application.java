package ru.learnup.java2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.learnup.java2.services.OperaEvent;
import ru.learnup.java2.services.OperaHouse;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class);

        OperaHouse operaHouse = ctx.getBean(OperaHouse.class);
        OperaEvent figaro = ctx.getBean(OperaEvent.class,
                "Женитьба Фигаро",
                "Опера-буффа Моцарта на итальянском языке, написанная на либретто Лоренцо да Понте по одноимённой пьесе Бомарше",
                16,
                600);
        OperaEvent nibelung = ctx.getBean(OperaEvent.class,
                "Кольцо нибелунга",
                "Цикл из четырёх эпических опер, основанных на реконструкциях германской мифологии, исландских сагах и средневековой поэме «Песнь о Нибелунгах»",
                12,
                600);
        OperaEvent butterfly = ctx.getBean(OperaEvent.class,
                "Мадам Баттерфляй",
                "Опера Джакомо Пуччини в трёх актах (в первой редакции в двух актах) на либретто Луиджи Иллика и Джузеппе Джакоза, по мотивам одноимённой драмы Дэвида Беласко (1900)",
                0,
                600);

        //добавление событий в театр
        operaHouse.add(figaro);
        operaHouse.add(nibelung);
        operaHouse.add(butterfly);
        operaHouse.add("Борис Годунов", " опера Модеста Мусоргского в четырёх действиях с прологом (в восьми, во второй редакции девяти картинах).", 12, 500);

        //все события
        System.out.println(operaHouse.getAllEvents());

        operaHouse.buyTheTicket(butterfly, 3);

        //покупка и возврат билетов
        operaHouse.buyTheTickets(figaro, 10, 11, 12, 13);
        System.out.println(operaHouse.getEvent(figaro.getName()));
        operaHouse.refundTheTickets(figaro, 12, 13);
        System.out.println(operaHouse.getEvent(figaro.getName()));

        operaHouse.remove("Кольцо нибелунга");
        System.out.println(operaHouse.getAllEvents());
    }
}
