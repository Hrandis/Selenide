import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class FormSendTest {

    LocalDate nowDate = LocalDate.now();
    LocalDate nextWeekDate = nowDate.plusDays(7);
    int year = nextWeekDate.getYear();
    int month = nextWeekDate.getMonthValue();
    int day = nextWeekDate.getDayOfMonth();
    String dateForForm;

    @BeforeEach
    public void NextWeekDate() {

        if (month < 10) {
            dateForForm = day + ".0" + month + "." + year;
        } else {
            dateForForm = day + "." + month + "." + year;
        }
    }

    @Test
    void shouldSendFormWithCorrectData() {
        open("http://localhost:9999");
        $("[data-test-id=\"city\"] input").setValue("Екатеринбург");
        $("[data-test-id=\"date\"] input").doubleClick();
        $("[data-test-id=\"date\"] input").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=\"date\"] input").setValue(dateForForm);
        $("[data-test-id=\"name\"] input").setValue("Иван Иванов");
        $("[data-test-id=\"phone\"] input").setValue("+79999999999");
        $("[data-test-id=\"agreement\"]").click();
        $(".button").click();
        $("[data-test-id=\"notification\"]").shouldBe(visible, Duration.ofSeconds(15));
    }
}