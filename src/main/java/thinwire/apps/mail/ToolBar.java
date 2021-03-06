/*
                             ThinWire(R) Mail Demo
                 Copyright (C) 2006-2007 Custom Credit Systems

  This library is free software; you can redistribute it and/or modify it under
  the terms of the GNU Lesser General Public License as published by the Free
  Software Foundation; either version 2.1 of the License, or (at your option) any
  later version.

  This library is distributed in the hope that it will be useful, but WITHOUT ANY
  WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
  PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.

  You should have received a copy of the GNU Lesser General Public License along
  with this library; if not, write to the Free Software Foundation, Inc., 59
  Temple Place, Suite 330, Boston, MA 02111-1307 USA

  Users interested in finding out more about the ThinWire framework should visit
  the ThinWire framework website at http://www.thinwire.com. For those interested
  in discussing the details of how this demo was built, you can contact the 
  developer via email at "Ted C. Howard" <tedchoward at gmail dot com>.
*/

package thinwire.apps.mail;

import java.util.HashMap;

import thinwire.ui.Button;
import thinwire.ui.Component;
import thinwire.ui.Panel;
import thinwire.ui.event.PropertyChangeEvent;
import thinwire.ui.event.PropertyChangeListener;

/**
 * The ToolBar is a Panel that contains a set of left-justified Buttons and
 * (optionally) one right justified Component. In MailTabSheet.java, the
 * ToolBar's rightComponent is a SearchField. The ToolBar provides a quick
 * interface for adding and accessing buttons.
 * 
 * @author Ted C. Howard
 */
class ToolBar extends Panel {
    private HashMap<String, Button> buttonMap;
    private int nextX;
    private Component rightComponent;

    private PropertyChangeListener sizeListener = new PropertyChangeListener() {
        public void propertyChange(PropertyChangeEvent ev) {
            int size = ((Integer) ev.getNewValue()) - (MailClient.BORDER_SIZE * 2);
            if (ev.getPropertyName().equals(PROPERTY_WIDTH)) {
                if (getChildren().contains(rightComponent))
                    rightComponent.setX(size - rightComponent.getWidth());
            }
        }
    };

    ToolBar() {
        buttonMap = new HashMap<String, Button>();
        nextX = 5;
        setHeight(30);
        addPropertyChangeListener(new String[] { PROPERTY_WIDTH, PROPERTY_HEIGHT }, sizeListener);
    }

    void addButton(String text) {
        addButton(text, null);
    }

    /**
     * Adds a button to the ToolBar. This method automatically calculates the
     * size and position of the new Button.
     * 
     * @param text
     * @param image
     */
    void addButton(String text, String image) {
        Button newButton = new Button();
        newButton.setText(text);
        if (image != null) newButton.setImage(image);
        newButton.setBounds(nextX, 3, text.length() * 7 + 20, 25);
        nextX = newButton.getX() + newButton.getWidth() + 5;
        getChildren().add(newButton);
        buttonMap.put(text, newButton);
    }

    /**
     * @param text
     * @return the Button specified by the text
     */
    Button getButton(String text) {
        return buttonMap.get(text);
    }

    /**
     * Places one Component on the ToolBar and aligns to to the right.
     * 
     * @param rightComponent
     */
    void setRightComponent(Component rightComponent) {
        if (getChildren().contains(this.rightComponent)) getChildren().remove(this.rightComponent);
        this.rightComponent = rightComponent;
        getChildren().add(this.rightComponent);
        this.rightComponent.getStyle().getBackground().setColor(getStyle().getBackground().getColor());
    }
}
