import synthesizer.GuitarString;

public class GuitarHero {

    public static void main(String[] args) {
        double CONCERT_A = 440.0;
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        GuitarString[] notes = new GuitarString[37];

        for (int i = 0; i < notes.length; i++) {
            notes[i] = new GuitarString(CONCERT_A * Math.pow(2, (i - 24.0) / 12));
        }

        while (true) {
            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = keyboard.indexOf(key);
                if (index != -1) {
                    notes[index].pluck();
                }
            }

        /* compute the superposition of samples */
            double sample = 0.0;
            for (int i = 0; i < notes.length; i++){
                sample += notes[i].sample();
            }


        /* play the sample on standard audio */
            StdAudio.play(sample);

        /* advance the simulation of each guitar string by one step */
            for (int i = 0; i < notes.length; i++){
                notes[i].tic();
            }
        }
    }
}

