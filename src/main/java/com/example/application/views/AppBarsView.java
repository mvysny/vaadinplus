package com.example.application.views;

import com.example.application.components.AppBar;
import com.example.application.components.Preview;
import com.example.application.themes.InputTheme;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.Lumo;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.vaadin.lineawesome.LineAwesomeIcon;

@PageTitle("App Bars")
@Route(value = "app-bars", layout = MainLayout.class)
public class AppBarsView extends View {

    public AppBarsView() {
        addClassNames(LumoUtility.Padding.Top.LARGE);

        RadioButtonGroup mode = new RadioButtonGroup("Mode");
        mode.setItems("Light", "Dark");
        mode.addValueChangeListener(e -> getChildren()
                .filter(child -> child instanceof Preview)
                .forEach(child -> child.getChildren()
                        .filter(grandChild -> grandChild instanceof AppBar)
                        .forEach(grandChild -> {
                            if (e.getValue().equals("Dark")) {
                                ((AppBar) grandChild).addThemeName(Lumo.DARK);
                            } else {
                                ((AppBar) grandChild).removeThemeName(Lumo.DARK);
                            }
                        })
                ));
        add(mode);

        addH2("Simple");
        addPreview(createAppBar());

        addH2("Primary Button");
        addPreview(createAppBarWithPrimaryButton());

        addH2("Search");
        addPreview(createAppBarWithSearch());

        addH2("Search (Middle)");
        addPreview(createAppBarWithSearchAlt());
    }

    private AppBar createAppBar() {
        AppBar appBar = new AppBar();
        appBar.addToStart(createIcon(), createAppNav());
        appBar.addToEnd(createButton(), createAvatar());
        return appBar;
    }

    private AppBar createAppBarWithPrimaryButton() {
        Button button = new Button("New Event", LineAwesomeIcon.PLUS_SOLID.create());
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        AppBar appBar = createAppBar();
        appBar.addToEnd(0, button);
        return appBar;
    }

    private AppBar createAppBarWithSearch() {
        AppBar appBar = createAppBar();
        appBar.addToEnd(0, createTextField());
        return appBar;
    }

    private AppBar createAppBarWithSearchAlt() {
        TextField textField = createTextField();
        textField.addClassNames(LumoUtility.Flex.GROW);
        textField.setMaxWidth(420, Unit.PIXELS);

        AppBar appBar = new AppBar();
        appBar.addToStart(createIcon());
        appBar.addToMiddle(textField);
        appBar.addToEnd(createButton(), createAvatar());
        return appBar;
    }

    private Component createIcon() {
        Component icon = LineAwesomeIcon.FEATHER_ALT_SOLID.create();
        icon.addClassNames(LumoUtility.TextColor.PRIMARY);
        ((HasSize) icon).setHeight("var(--lumo-icon-size-m)");
        ((HasSize) icon).setWidth("var(--lumo-icon-size-m)");
        return icon;
    }

    private SideNav createAppNav() {
        SideNav nav = new SideNav();
        nav.addItem(new SideNavItem("App Bars", AppBarsView.class, LineAwesomeIcon.BARS_SOLID.create()));
        nav.addItem(new SideNavItem("Checkboxes", CheckboxesView.class, LineAwesomeIcon.CHECK_SQUARE.create()));
        nav.addItem(new SideNavItem("Empty States", EmptyStatesView.class, LineAwesomeIcon.FILE.create()));
        nav.addItem(new SideNavItem("Headers", HeadersView.class, LineAwesomeIcon.HEADING_SOLID.create()));
        nav.getChildren().forEach(navItem -> navItem.addClassNames(LumoUtility.Display.INLINE_BLOCK));
        return nav;
    }

    private TextField createTextField() {
        TextField textField = new TextField("Search");
        textField.addThemeNames(InputTheme.HIDE_LABEL);
        textField.setPlaceholder("Search");
        textField.setPrefixComponent(LineAwesomeIcon.SEARCH_SOLID.create());
        return textField;
    }

    private Button createButton() {
        Button button = new Button(LineAwesomeIcon.BELL.create());
        button.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        button.getElement().setAttribute("aria-label", "Notifications");
        return button;
    }

    private Avatar createAvatar() {
        Avatar avatar = new Avatar("John Smith");
        avatar.setImage("https://images.unsplash.com/photo-1529626455594-4ff0802cfb7e?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=facearea&facepad=2&w=256&h=256&q=80");
        return avatar;
    }

}
