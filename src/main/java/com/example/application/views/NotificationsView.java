package com.example.application.views;

import com.example.application.components.Notification;
import com.example.application.utilities.TextColor;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.ListItem;
import com.vaadin.flow.component.html.UnorderedList;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;

@PageTitle("Notifications")
@Route(value = "notifications", layout = MainLayout.class)
public class NotificationsView extends View {

    public static final String LOREM_IPSUM_PT1 = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
    public static final String LOREM_IPSUM_PT2 = "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.";

    public NotificationsView() {
        addH2("Info");
        add(createNotification());

        addH2("Success");
        add(createSuccessNotification());

        addH2("Error");
        add(createErrorNotification());

        addH2("Theme: Border");
        Notification notification = createNotification();
        notification.setBorder(true);
        add(notification);

        addH2("Theme: Start Border");
        notification = createSuccessNotification();
        notification.setAccentBorder(true);
        add(notification);

        addH2("Custom Text Color");
        notification = createErrorNotification();
        notification.setTextColor(TextColor.ERROR);
        add(notification);
    }

    private Notification createNotification() {
        Notification notification = new Notification("Update available", LOREM_IPSUM_PT1, Notification.Type.INFO);
        notification.setActions(new Button("Update"));
        return notification;
    }

    private Notification createSuccessNotification() {
        Notification notification = new Notification(
                "Update successful", LOREM_IPSUM_PT2, Notification.Type.SUCCESS
        );
        notification.setActions(new Button("Details"));
        return notification;
    }

    private Notification createErrorNotification() {
        UnorderedList list = new UnorderedList(
                new ListItem(LOREM_IPSUM_PT1),
                new ListItem(LOREM_IPSUM_PT2)
        );
        list.addClassNames(LumoUtility.Margin.NONE, LumoUtility.Padding.Start.MEDIUM);
        return new Notification("There are 2 errors:", list, Notification.Type.ERROR);
    }

}
