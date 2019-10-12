package com.bytezone.diskbrowser.gui;

import java.awt.Dimension;
import java.util.prefs.Preferences;

import javax.swing.JFrame;

public class WindowSaver
{
  private final Preferences prefs;
  private final JFrame frame;
  private final String key;

  public WindowSaver (Preferences prefs, JFrame frame, String key)
  {
    this.prefs = prefs;
    this.frame = frame;
    this.key = key;
  }

  public void saveWindow ()
  {
    prefs.putInt (key + "X", frame.getX ());
    prefs.putInt (key + "Y", frame.getY ());
    prefs.putInt (key + "Height", frame.getHeight ());
    prefs.putInt (key + "Width", frame.getWidth ());
  }

  public boolean restoreWindow ()
  {
    int x = prefs.getInt (key + "X", -1);
    int y = prefs.getInt (key + "Y", -1);
    int height = prefs.getInt (key + "Height", -1);
    int width = prefs.getInt (key + "Width", -1);

    Dimension screen = java.awt.Toolkit.getDefaultToolkit ().getScreenSize ();

    if (width < 0)                                    // nothing to restore
    {
      frame.setLocation (100, 100);
      frame.setSize (1000, 600);
      frame.setLocationRelativeTo (null);             // centre
      return false;
    }

    if (width > screen.getWidth () - 15)
      width = (int) (screen.getWidth () - 15);

    frame.setSize (width, height);
    frame.setLocation (x, y);

    return true;
  }
}