package org.fenixedu.ulisboa.specifications.ui.teacher;

import java.util.Optional;

import org.fenixedu.bennu.core.groups.Group;
import org.fenixedu.cms.domain.MenuItem;
import org.fenixedu.commons.i18n.LocalizedString;

import com.google.common.base.Objects;
import com.google.common.base.Strings;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import pt.ist.fenixframework.FenixFramework;

public class PagesAdminBean {
    private static final JsonParser PARSER = new JsonParser();
    private Group canViewGroup;
    private MenuItem menuItem;
    private MenuItem parent;
    private LocalizedString title;
    private LocalizedString body;
    private LocalizedString excerpt;
    private final Boolean visible;

    public PagesAdminBean(final String json) {
        this(PARSER.parse(json).getAsJsonObject());
    }

    public PagesAdminBean(final JsonObject jsonObj) {
        if (asString(jsonObj, "menuItemId").isPresent()) {
            this.menuItem = menuItem(asString(jsonObj, "menuItemId").get());
        }
        if (asString(jsonObj, "menuItemParentId").isPresent()) {
            this.parent = menuItem(asString(jsonObj, "menuItemParentId").get());
        }
        if (jsonObj.has("title") && jsonObj.get("title") != null && !jsonObj.get("title").isJsonNull()) {
            this.title = LocalizedString.fromJson(jsonObj.get("title"));
        }
        if (jsonObj.has("body") && jsonObj.get("body") != null && !jsonObj.get("body").isJsonNull()) {
            this.body = LocalizedString.fromJson(jsonObj.get("body"));
        }
        if (jsonObj.has("excerpt") && jsonObj.get("excerpt") != null && !jsonObj.get("excerpt").isJsonNull()) {
            this.excerpt = LocalizedString.fromJson(jsonObj.get("excerpt"));
        }
        if (asString(jsonObj, "canViewGroupIndex").isPresent()) {
            Integer.parseInt(asString(jsonObj, "canViewGroupIndex").get());
            this.canViewGroup = executionCourseGroup(Integer.parseInt(asString(jsonObj, "canViewGroupIndex").get()));
        }
        this.visible = jsonObj.has("visible") ? jsonObj.get("visible").getAsBoolean() : null;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public MenuItem getParent() {
        return parent;
    }

    public LocalizedString getTitle() {
        return title;
    }

    public LocalizedString getBody() {
        return body;
    }

    public LocalizedString getExcerpt() {
        return excerpt;
    }

    public Group getCanViewGroup() {
        return this.canViewGroup;
    }

    protected static Optional<String> asString(final JsonObject jsonObject, final String field) {
        if (jsonObject.has(field)) {
            if (jsonObject.get(field) != null && !jsonObject.isJsonNull() && jsonObject.get(field).isJsonPrimitive()
                    && !"null".equals(jsonObject.get(field).getAsString())) {
                return Optional.ofNullable(jsonObject.get(field).getAsString());
            }
        }
        return Optional.empty();
    }

    protected static MenuItem menuItem(final String menuItemId) {
        return Strings.isNullOrEmpty(menuItemId) ? null : FenixFramework.getDomainObject(menuItemId);
    }

    private Group executionCourseGroup(final int canViewGroupIndex) {
        return PagesAdminService.permissionGroups(menuItem.getPage().getSite()).get(canViewGroupIndex);
    }

    public Boolean isVisible() {
        return visible;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("menuItem", menuItem).add("parent", parent).add("title", title).add("body", body)
                .add("excerpt", excerpt).toString();
    }

}