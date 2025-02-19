package com.example.application.components.list;

import com.example.application.components.Layout;
import com.example.application.utilities.*;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.theme.lumo.LumoUtility;

public class ImageListItem extends com.vaadin.flow.component.html.ListItem {

    // Style
    private AlignItems alignItems;
    private FlexDirection flexDirection;
    private ColumnGap colGap;
    private RowGap rowGap;

    // Components
    private Layout image;
    private Layout row;
    private Layout column;
    private Layout primary;
    private Layout secondary;
    private Layout actions;

    public ImageListItem(String src, String alt, String primary, String secondary, Component... actions) {
        this(new Image(src, alt), new Text(primary), new Text(secondary), actions);
    }

    public ImageListItem(Image image, Component primary, Component secondary, Component... actions) {
        addClassNames(LumoUtility.Background.BASE, LumoUtility.Display.FLEX);
        setFlexDirection(FlexDirection.COLUMN);

        this.image = new Layout();
        this.image.setFlexGrow(1, this.image);
        setImage(image);

        this.primary = new Layout();
        setPrimary(primary);

        this.secondary = new Layout();
        this.secondary.addClassNames(LumoUtility.FontSize.SMALL, LumoUtility.TextColor.SECONDARY);
        setSecondary(secondary);

        this.column = new Layout(this.primary, this.secondary);
        this.column.addClassNames(LumoUtility.Padding.Start.XSMALL);
        this.column.setFlexDirection(FlexLayout.FlexDirection.COLUMN);
        this.column.setFlexGrow(1, this.column);

        this.actions = new Layout();
        setActions(actions);

        this.row = new Layout(this.column, this.actions);
        this.row.setAlignItems(Alignment.CENTER);
        this.row.addClassNames(
                LumoUtility.Padding.End.XSMALL, LumoUtility.Padding.Start.SMALL, LumoUtility.Padding.Vertical.SMALL
        );

        add(this.image, this.row);
    }

    /**
     * Sets the image.
     */
    public void setImage(Image image) {
        this.image.removeAll();
        if (image != null) {
            image.addClassNames(LumoUtility.MaxWidth.FULL);
            this.image.add(image);
        }
    }

    /**
     * Sets the primary content.
     */
    public void setPrimary(Component... components) {
        this.primary.removeAll();
        if (components != null) {
            for (Component component : components) {
                if (component != null) {
                    this.primary.add(component);
                }
            }
        }
        this.primary.setVisible(this.primary.getComponentCount() > 0);
    }

    /**
     * Sets the secondary content.
     */
    public void setSecondary(Component... components) {
        this.secondary.removeAll();
        if (components != null) {
            for (Component component : components) {
                if (component != null) {
                    this.secondary.add(component);
                }
            }
        }
        this.secondary.setVisible(this.secondary.getComponentCount() > 0);
    }

    /**
     * Sets the actions.
     */
    public void setActions(Component... components) {
        this.actions.removeAll();
        if (components != null) {
            for (Component component : components) {
                if (component != null) {
                    this.actions.add(component);
                }
            }
        }
        this.actions.setVisible(this.actions.getComponentCount() > 0);
    }


    /**
     * Sets the item alignment.
     */
    public void setAlignItems(AlignItems alignItems) {
        if (this.alignItems != null) {
            removeClassNames(this.alignItems.getClassName());
        }
        addClassNames(alignItems.getClassName());
        this.alignItems = alignItems;
    }

    /**
     * Sets the flex direction.
     */
    public void setFlexDirection(FlexDirection flexDirection) {
        if (this.flexDirection != null) {
            removeClassNames(this.flexDirection.getClassName());
        }
        addClassNames(flexDirection.getClassName());
        this.flexDirection = flexDirection;
    }

    /**
     * Sets both the column (horizontal) and row (vertical) gap between components.
     */
    public void setGap(Gap gap) {
        setColumnGap(gap);
        setRowGap(gap);
    }

    /**
     * Sets the column (horizontal) gap between components.
     */
    public void setColumnGap(Gap gap) {
        removeColumnGap();
        this.addClassNames(gap.getColumnGap().getClassName());
        this.colGap = gap.getColumnGap();
    }

    /**
     * Sets the row (vertical) gap between components.
     */
    public void setRowGap(Gap gap) {
        removeRowGap();
        this.addClassNames(gap.getRowGap().getClassName());
        this.rowGap = gap.getRowGap();
    }

    /**
     * Removes both the column (horizontal) and row (vertical) gap between components.
     */
    public void removeGap() {
        removeColumnGap();
        removeRowGap();
    }

    /**
     * Removes the column (horizontal) gap between components.
     */
    public void removeColumnGap() {
        if (this.colGap != null) {
            this.removeClassName(this.colGap.getClassName());
        }
        this.colGap = null;
    }

    /**
     * Removes the row (vertical) gap between components.
     */
    public void removeRowGap() {
        if (this.rowGap != null) {
            this.removeClassName(this.rowGap.getClassName());
        }
        this.rowGap = null;
    }

}
