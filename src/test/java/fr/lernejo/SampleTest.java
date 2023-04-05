package fr.lernejo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class SampleTest {

    private final Sample sample = new Sample();

    @Test
    void add_2_and_2_should_return_4() {
        int result = sample.op(Sample.Operation.ADD, 2, 2);

        Assertions.assertThat(result)
            .as("Addition of 2 and 2")
            .isEqualTo(4);
    }

    @Test
    void mult_2_and_2_should_return_4(){
        int result = sample.op(Sample.Operation.MULT, 2, 2);
        Assertions.assertThat(result)
            .as("Multiplication of 2 and 2")
            .isEqualTo(4);
    }
    @Test
    void fact_7_should_return_5040_and_show_Exception(){
        Sample operation1 = new Sample();
        Integer resultfloat1 = operation1.fact(7);
        Assertions.assertThat(resultfloat1).isEqualTo(5040);
        Sample operation2 = new Sample();
        Assertions.assertThatException().isThrownBy(() -> operation2.fact(-7)).withMessage("N should be positive");

    }

}
