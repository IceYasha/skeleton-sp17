public class NBody {

  public static double readRadius(String fileName) {
    In in = new In(fileName);
    int i = 0;
    double rank = 0.0;
    while(!in.isEmpty()) {
      rank = in.readDouble();
      if (i == 1) return rank;
      i += 1;
    }
    return rank;
  }

  public static Planet[] readPlanets(String fileName) {
    In in = new In(fileName);
    int number = in.readInt();
    int i = 0;
    int j = 0;
    Planet[] planets = new Planet[number];
    while(!in.isEmpty()) {
      if (i == 0) {
        in.readDouble();
        i++;
        continue;
      }
			double xPos = in.readDouble();
      double yPos = in.readDouble();
      double xVel = in.readDouble();
      double yVel = in.readDouble();
      double mass = in.readDouble();
      String img = in.readString();
      Planet p = new Planet(xPos,yPos,xVel,yVel,mass,img);
      planets[j++] = p;
      if (j == number) return planets;
    }
    return planets;
  }


  public static void main(String[] args) {
    double T = Double.parseDouble(args[0]);
    double dt = Double.parseDouble(args[1]);
    String filename = args[2];
    double radius = readRadius(filename);
    Planet[] planets = readPlanets(filename);
    int planetsLength = planets.length;
    double t = 0.0;

    StdDraw.setScale(-radius, radius);
    StdAudio.play("audio/2001.mid");
    while(t < T) {
      double[] xForces = new double[planetsLength];
      double[] yForces = new double[planetsLength];
      for(int i = 0; i < planetsLength; i++) {
        xForces[i] = planets[i].calcNetForceExertedByX(planets);
        yForces[i] = planets[i].calcNetForceExertedByY(planets);
      }

      for(int i = 0; i < planetsLength; i++) {
        planets[i].update(dt, xForces[i], yForces[i]);
      }

      StdDraw.clear();
      StdDraw.picture(0, 0, "images/starfield.jpg");

      for(int i = 0;i < planetsLength; i++) {
        planets[i].draw();
      }

      StdDraw.show(10);
      t += dt;
    }

    StdOut.printf("%d\n", planets.length);
    StdOut.printf("%.2e\n", radius);
    for (int i = 0; i < planets.length; i++) {
	     StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
   		planets[i].xxPos, planets[i].yyPos, planets[i].xxVel, planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
    }
  }
}
