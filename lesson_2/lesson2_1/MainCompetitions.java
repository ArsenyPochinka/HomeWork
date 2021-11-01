package lesson_2.lesson2_1;

public class MainCompetitions {
    public static void main(String[] args) {
        RunJump[] team = {new Cat(), new Human(), new Robot()};
        Obstacle[] obstacle = {new Treadmill(), new Wall()};
        for(RunJump t: team) {
            boolean result=false;
            for(Obstacle o: obstacle) {
                if (o == obstacle[0]) {
                    t.run();
                    result=o.overcoming(t.getMAXRUN());
                }
                if(result && o == obstacle[1]) {
                    t.jump();
                    o.overcoming(t.getMAXJUMP());
                }
            }
            System.out.println();
        }
    }
}
