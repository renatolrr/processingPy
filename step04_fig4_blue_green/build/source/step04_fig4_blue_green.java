import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class step04_fig4_blue_green extends PApplet {

PImage mapImage;
Table locationTable;
int rowCount;

Table dataTable;
float dataMin = MAX_FLOAT;
float dataMax = MIN_FLOAT;


public void setup() {
  
  mapImage = loadImage("map.png");
  locationTable = new Table("locations.tsv");
  rowCount = locationTable.getRowCount();

  // Read the data table
  dataTable = new Table("random.tsv");

  // Find the minimum and maximum values
  for (int row = 0; row < rowCount; row++) {
    float value = dataTable.getFloat(row, 1);
    if (value > dataMax) {
      dataMax = value;
    }
    if (value < dataMin) {
      dataMin = value;
    }
  }
  
  noStroke();
}


public void draw() {
  background(255);
  tint(255, 160);
  image(mapImage, 0, 0);

  for (int row = 0; row < rowCount; row++) {
    String abbrev = dataTable.getRowName(row);
    float x = locationTable.getFloat(abbrev, 1);
    float y = locationTable.getFloat(abbrev, 2);
    drawData(x, y, abbrev);
  }
}


public void drawData(float x, float y, String abbrev) {
  float value = dataTable.getFloat(abbrev, 1);
  float percent = norm(value, dataMin, dataMax);
  int between = lerpColor(0xff296F34, 0xff61E2F0, percent);
  fill(between);
  ellipse(x, y, 15, 15);
}
class Table {
  int rowCount;
  String[][] data;
  
  
  Table(String filename) {
    String[] rows = loadStrings(filename);
    data = new String[rows.length][];
    
    for (int i = 0; i < rows.length; i++) {
      if (trim(rows[i]).length() == 0) {
        continue; // skip empty rows
      }
      if (rows[i].startsWith("#")) {
        continue;  // skip comment lines
      }
      
      // split the row on the tabs
      String[] pieces = split(rows[i], TAB);
      // copy to the table array
      data[rowCount] = pieces;
      rowCount++;
      
      // this could be done in one fell swoop via:
      //data[rowCount++] = split(rows[i], TAB);
    }
    // resize the 'data' array as necessary
    data = (String[][]) subset(data, 0, rowCount);
  }
  
  
  public int getRowCount() {
    return rowCount;
  }
  
  
  // find a row by its name, returns -1 if no row found
  public int getRowIndex(String name) {
    for (int i = 0; i < rowCount; i++) {
      if (data[i][0].equals(name)) {
        return i;
      }
    }
    println("No row named '" + name + "' was found");
    return -1;
  }
  
  
  public String getRowName(int row) {
    return getString(row, 0);
  }


  public String getString(int rowIndex, int column) {
    return data[rowIndex][column];
  }

  
  public String getString(String rowName, int column) {
    return getString(getRowIndex(rowName), column);
  }

  
  public int getInt(String rowName, int column) {
    return parseInt(getString(rowName, column));
  }

  
  public int getInt(int rowIndex, int column) {
    return parseInt(getString(rowIndex, column));
  }

  
  public float getFloat(String rowName, int column) {
    return parseFloat(getString(rowName, column));
  }

  
  public float getFloat(int rowIndex, int column) {
    return parseFloat(getString(rowIndex, column));
  }  
}
  public void settings() {  size(640, 400);  smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#666666", "--stop-color=#cccccc", "step04_fig4_blue_green" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
