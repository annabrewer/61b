public class Planet {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public Planet(Planet p) {
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	public Planet(double xP, double yP, double xV,
              double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public double calcDistance(Planet p) {
		double xDist = p.xxPos - xxPos;
		double yDist = p.yyPos - yyPos;
		return Math.sqrt(xDist*xDist + yDist*yDist);
	}

	public double calcForceExertedBy(Planet p) {
		double dist = calcDistance(p);
		double g = 6.67 /100000000000.0;
		return (g * mass * p.mass) / (dist*dist);
	}

	public double calcForceExertedByX (Planet p) {
		return calcForceExertedBy(p) * (p.xxPos - xxPos) / calcDistance(p);
	}

	public double calcForceExertedByY (Planet p) {
		return calcForceExertedBy(p) * (p.yyPos - yyPos) / calcDistance(p);
	}

	public double calcNetForceExertedByX (Planet[] planets) {
		double f = 0;
		for (Planet p : planets) {
			if (! this.equals(p)) {
				f += calcForceExertedByX(p);
			}
		}
		return f;
	}

	public double calcNetForceExertedByY (Planet[] planets) {
		double f = 0;
		for (Planet p : planets) {
			if (! this.equals(p)) {
				f += calcForceExertedByY(p);
			}
		}
		return f;
	}

	public void update (double dT, double fX, double fY) {
		double accX = fX / mass;
		double accY = fY / mass;
		xxVel += accX*dT;
		yyVel += accY*dT;
		xxPos += xxVel*dT;
		yyPos += yyVel*dT;
	}

	public void draw() {
		StdDraw.picture(xxPos, yyPos, "images/"+imgFileName);
	}
}
