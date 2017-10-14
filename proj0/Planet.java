import java.math.*;

public class Planet {
    double xxPos;
    double yyPos;
    double xxVel;
    double yyVel;
    double mass;
    String imgFileName;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img) {
                    xxPos = xP;
                    yyPos = yP;
                    xxVel = xV;
                    yyVel = yV;
                    mass = m;
                    imgFileName = img;
                  }

    public Planet(Planet p) {
      xxPos = p.xxPos;
      yyPos = p.yyPos;
      xxVel = p.xxVel;
      yyVel = p.yyVel;
      mass = p.mass;
      imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
      return Math.sqrt(Math.pow((this.xxPos-p.xxPos),2)+
                       Math.pow((this.yyPos-p.yyPos),2));
    }

    public double calcForceExertedBy(Planet p) {
      double g = 6.67 * Math.pow(10, -11);
      double r = this.calcDistance(p);
      return g * this.mass * p.mass / (r * r) ;
    }

    public double calcForceExertedByX(Planet p) {
      double dx =  p.xxPos - this.xxPos;
      double f = this.calcForceExertedBy(p);
      double r = this.calcDistance(p);
      return f * dx / r;
    }

    public double calcForceExertedByY(Planet p) {
      double dy = p.yyPos - this.yyPos;
      double f = this.calcForceExertedBy(p);
      double r = this.calcDistance(p);
      return f * dy / r;
    }

    public double calcNetForceExertedByX(Planet[] allPlanets) {
      double forceX = 0.0;
      for(int i=0; i < allPlanets.length; i++) {
        if (!this.equals(allPlanets[i]))
        forceX += this.calcForceExertedByX(allPlanets[i]);
      }
      return forceX;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets) {
      double forceY = 0.0;
      for(int i=0; i < allPlanets.length; i++) {
        if (!this.equals(allPlanets[i]))
        forceY += this.calcForceExertedByY(allPlanets[i]);
      }
      return forceY;
    }

    public void update(double dt, double fX, double fY) {
      double aX = fX / this.mass;
      double aY = fY / this.mass;
      this.xxVel += dt * aX;
      this.yyVel += dt * aY;
      this.xxPos += dt * this.xxVel;
      this.yyPos += dt * this.yyVel;
    }

    public void draw() {
      StdDraw.picture(xxPos, yyPos, "images/"+imgFileName);
    }
}
