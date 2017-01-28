import java.io.*;
import java.util.*;

public class NBody {	

	public static void main(String[] args) {
		double t = Double.parseDouble(args[0]);
		double dT = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		Planet[] planets= readPlanets(filename);
		StdDraw.setXscale(-radius, radius);
		StdDraw.setYscale(-radius, radius);
		StdDraw.clear();
		StdDraw.picture(0, 0, "images/starfield.jpg");
		StdDraw.show();
		for (Planet p : planets) {
			p.draw();
		}
		double time = 0;
		while (time < t) {
			double[] xForces = new double[planets.length];
			double[] yForces = new double[planets.length];
			for (int i = 0; i < planets.length; i++) {
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}
			for (int i = 0; i < planets.length; i++) {
				planets[i].update(dT, xForces[i], yForces[i]);
			}
			StdDraw.picture(0, 0, "images/starfield.jpg");
			for (Planet p : planets) {
				p.draw();
			}
			StdDraw.show(10);
			time += dT;
		}
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
   			planets[i].xxPos, planets[i].yyPos, planets[i].xxVel, planets[i].yyVel, planets[i].mass, planets[i].imgFileName);	
		}
	}

	public static double readRadius(String filename) {
		In in = new In(filename);
		/* Keep looking until the file is empty. */
		in.readInt();
		return in.readDouble();
	}

	public static Planet[] readPlanets(String filename) {
		In in = new In(filename);
		int num = in.readInt();
		in.readDouble();
		Planet[] planets = new Planet[num];
		for (int i=0; i<num; i++) {
			planets[i] = new Planet (in.readDouble(), in.readDouble(), in.readDouble(), 
									in.readDouble(), in.readDouble(), in.readString());
		}
		return planets;
	}
} 