# Material-Calendar-View

Material-Calendar-View is a simple and customizable calendar widget for Android based on Material Design. The widget has two funcionalities: a date picker to select dates (available as an XML widget and a dialog) and a classic calendar. The date picker can work either as a single day picker, many days picker or range picker.

We described a simple usage of the component [in this article](http://applandeo.com/blog/material-calendar-view-customized-calendar-widget-android/).

![34562830-637ddbae-f150-11e7-8004-9024fb84a883](https://user-images.githubusercontent.com/2614225/46456381-f72da200-c7ae-11e8-8284-1799fe83a1c9.png) ![device-2018-01-04-125741](https://user-images.githubusercontent.com/2614225/34562842-709a71ee-f150-11e7-966b-cbbe6169b88b.png) ![device-2018-01-04-125831](https://user-images.githubusercontent.com/2614225/34562859-7bd3e64e-f150-11e7-98f4-f00bafe846c6.png) ![device-2018-01-04-125915](https://user-images.githubusercontent.com/2614225/34562878-8f382f06-f150-11e7-97e4-5ac9babe5aa8.png)




## Features
* Material Design
* Single date picker
* Many dates picker
* Range picker
* Events icons
* Fully colors customization


## How to use?
Make sure you are using the newest **com.android.support:appcompat-v7**.

Make sure you are using Java 8 in your project. If not, add below code to **build.gradle** file:
```
android {
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
}
```

Make sure you have defined the **jcenter()** repository in project's **build.gradle** file:
```
allprojects {
    repositories {
        jcenter()
    }
}
```

Add the dependency to module's **build.gradle** file:
```
dependencies {
    implementation 'com.applandeo:material-calendar-view:1.7.0'
}
```
or if you want to use the **kotlin** version of our library:
```
dependencies {
    implementation 'com.applandeo:material-calendar-view:1.8.0-rc01'
}
```
or if you want to use very early version with CalendarDay support:
```
dependencies {
    implementation 'com.applandeo:material-calendar-view:1.9.0-alpha03'
}
```

To your **XML layout** file add:
```xml
<com.applandeo.materialcalendarview.CalendarView
    android:id="@+id/calendarView"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />
```

### Adding events with icons:
```java
List<EventDay> events = new ArrayList<>();

Calendar calendar = Calendar.getInstance();
events.add(new EventDay(calendar, R.drawable.sample_icon));
//or
events.add(new EventDay(calendar, new Drawable()));
//or if you want to specify event label color
events.add(new EventDay(calendar, R.drawable.sample_icon, Color.parseColor("#228B22")));

CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);
calendarView.setEvents(events);
```

### How to create icons?
#### Drawable with text:
You can use our utils method to create Drawable with text
```java
CalendarUtils.getDrawableText(Context context, String text, Typeface typeface, int color, int size);
````
#### Dots indicator:
Take a look at [sample_three_icons.xml](https://github.com/Applandeo/Material-Calendar-View/blob/master/sample/src/main/res/drawable/sample_three_icons.xml) and adjust it to your project

### Clicks handling:
```java
calendarView.setOnDayClickListener(new OnDayClickListener() {
    @Override
    public void onDayClick(EventDay eventDay) {
        Calendar clickedDayCalendar = eventDay.getCalendar();    
    }
});
```

### Getting a selected days in the picker mode:
If you want to get all selected days, especially if you use multi date or range picker you should use the following code:
```java
List<Calendar> selectedDates = calendarView.getSelectedDates();
```
...or if you want to get the first selected day, for example in case of using single date picker, you can use:
```java
Calendar selectedDate = calendarView.getFirstSelectedDate();
```

### Setting a current date:
```java
Calendar calendar = Calendar.getInstance();
calendar.set(2019, 7, 5);
        
calendarView.setDate(calendar);
```

### Setting minumum and maximum dates:
```java
Calendar min = Calendar.getInstance();
Calendar max = Calendar.getInstance();

calendarView.setMinimumDate(min);
calendarView.setMaximumDate(max);
```

### Setting disabled dates:
```java
List<Calendar> calendars = new ArrayList<>();
calendarView.setDisabledDays(calendars);
```

### Setting highlighted days:
```java
List<Calendar> calendars = new ArrayList<>();
calendarView.setHighlightedDays(calendars);
```

### Setting selected dates:
```java
List<Calendar> calendars = new ArrayList<>();
calendarView.setSelectedDates(calendars);
```

#### Caution!
* Don't pass more than one calendar object to method above if your calendar type is `CalendarView.ONE_DAY_PICKER`.
* If your calendar type is `CalendarView.RANGE_PICKER` you have to pass full dates range. To get it you can use our utils method `CalendarUtils.getDatesRange(Calendar firstDay, Calendar lastDay)`.

### Previous and forward page change listeners:
```java
calendarView.setOnPreviousPageChangeListener(new OnCalendarPageChangeListener() {
    @Override
    public void onChange() {
        ...
    }
});

calendarView.setOnForwardPageChangeListener(new OnCalendarPageChangeListener() {
    @Override
    public void onChange() {
        ...
    }
});
```

## Customization
If you want to use calendar in the picker mode, you have to use the following tags:
* ```app:type="one_day_picker"```
* ```app:type="many_days_picker"```
* ```app:type="range_picker"```

If you want to display event icons in the picker mode, add:
* ```app:eventsEnabled="true"```

#### Colors customization:
* Header color: ```app:headerColor="[color]"```
* Header label color: ```app:headerLabelColor="[color]"```
* Previous button image resource: ```app:previousButtonSrc="[drawable]"```
* Forward button image resource: ```app:forwardButtonSrc="[drawable]"```
* Abbreviations bar color: ```app:abbreviationsBarColor="[color]"```
* Abbreviations labels color: ```app:abbreviationsLabelsColor="[color]"```
* Calendar pages color: ```app:pagesColor="[color]"```
* Selection color in picker mode: ```app:selectionColor="[color]"```
* Selection label color in picker mode: ```app:selectionLabelColor="[color]"```
* Days labels color: ```app:daysLabelsColor="[color]"```
* Color of visible days labels from previous and next month page: ```app:anotherMonthsDaysLabelsColor="[color]"```
* Disabled days labels color: ```app:disabledDaysLabelsColor="[color]"```
* Highlighted days labels color: ```app:highlightedDaysLabelsColor="[color]"```
* Today label color: ```app:todayLabelColor="[color]"```

...or in code:

```java
CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);
calendarView.setHeaderColor([color]);
calendarView.setHeaderLabelColor([color]);
calendarView.setForwardButtonImage([drawable]);
calendarView.setPreviousButtonImage([drawable]);
```
#### Disable month swipe:
If you want to disable the swipe gesture to change the month, you have to use the following tag: 
* ```app:swipeEnabled="false"```

...or in code:

```java
calendarView.setSwipeEnabled(false);
```

#### Translations:
To translate months names, abbreviations of days, "TODAY", "OK" and "CANCEL" buttons, just add below tags to your `strings.xml` file:
```xml
<string name="material_calendar_monday">M</string>
<string name="material_calendar_tuesday">T</string>
<string name="material_calendar_wednesday">W</string>
<string name="material_calendar_thursday">T</string>
<string name="material_calendar_friday">F</string>
<string name="material_calendar_saturday">S</string>
<string name="material_calendar_sunday">S</string>

<array name="material_calendar_months_array">
    <item>January</item>
    <item>February</item>
    <item>March</item>
    <item>April</item>
    <item>May</item>
    <item>June</item>
    <item>July</item>
    <item>August</item>
    <item>September</item>
    <item>October</item>
    <item>November</item>
    <item>December</item>
</array>

<string name="material_calendar_today_button">Today</string>
<string name="material_calendar_positive_button">OK</string>
<string name="material_calendar_negative_button">Cancel</string>
```

## Date Picker Dialog
```java
DatePickerBuilder builder = new DatePickerBuilder(this, listener)
                .pickerType(CalendarView.ONE_DAY_PICKER);

DatePicker datePicker = builder.build();
datePicker.show();
```

To use another picker type replace `CalendarView.ONE_DAY_PICKER` with `CalendarView.MANY_DAYS_PICKER` or `CalendarView.RANGE_PICKER`.

#### Getting date handling:
```java
private OnSelectDateListener listener = new OnSelectDateListener() {
    @Override
    public void onSelect(List<Calendar> calendars) {
        ...
    }
};
```

#### Customization:
```java
new DatePickerBuilder(this, listener)
        .setDate(Calendar.getInstance()) // Initial date as Calendar object
        .setMinimumDate(Calendar.getInstance()) // Minimum available date
        .setMaximumDate(Calendar.getInstance()) // Maximum available date
        .setDisabledDays(List<Calendar>) /// List of disabled days
        .setHeaderColor(R.color.color) // Color of the dialog header
        .setHeaderLabelColor(R.color.color) // Color of the header label
        .setPreviousButtonSrc(R.drawable.drawable) // Custom drawable of the previous arrow
        .setForwardButtonSrc(R.drawable.drawable) // Custom drawable of the forward arrow
        .setPreviousPageChangeListener(new OnCalendarPageChangeListener(){}) // Listener called when scroll to the previous page
        .setForwardPageChangeListener(new OnCalendarPageChangeListener(){}) // Listener called when scroll to the next page
        .setAbbreviationsBarColor(R.color.color) // Color of bar with day symbols
        .setAbbreviationsLabelsColor(R.color.color) // Color of symbol labels
        .setAbbreviationsBarVisibility(int) // Visibility of abbreviations bar
        .setPagesColor(R.color.sampleLighter) // Color of the calendar background
        .setSelectionColor(R.color.color) // Color of the selection circle
        .setSelectionLabelColor(R.color.color) // Color of the label in the circle
        .setDaysLabelsColor(R.color.color) // Color of days numbers
        .setAnotherMonthsDaysLabelsColor(R.color.color) // Color of visible days numbers from previous and next month page
        .setDisabledDaysLabelsColor(R.color.color) // Color of disabled days numbers
        .setHighlightedDaysLabelsColor(R.color.color) // Color of highlighted days numbers
        .setTodayColor(R.color.color) // Color of the present day background
        .setTodayLabelColor(R.color.color) // Color of the today number
        .setDialogButtonsColor(R.color.color); // Color of "Cancel" and "OK" buttons
        .setMaximumDaysRange(int) // Maximum number of selectable days in range mode
        .setNavigationVisibility(int) // Navigation buttons visibility
```

## Changelog

#### Version 1.8.0-rc01
* Migrated to kotlin

#### Version 1.7.0:
* Added ability to set maximum selectable days range
* Added more color customizations (Event label, today background)
* Added ability to hide date picker navigation buttons
* Added selected/focused state to calendar days (thanks [victor-accarini](https://github.com/victor-accarini))
* Added a setEvent option for the DatePickerBuilder (thanks [victor-accarini](https://github.com/victor-accarini))

#### Version 1.6.0:
* Migration to AndroidX
* Added ability to set highlighted days (thanks [domyn](https://github.com/domyn))
* Added Javadocs for DatePickerBuilder (many thanks [EdricChan03](https://github.com/EdricChan03))

#### Version 1.5.0:
* Added support for events (images) in picker calendars (many thanks [thavelka](https://github.com/thavelka) for your contribution)
--> [Customization](https://github.com/Applandeo/Material-Calendar-View#customization)
* Added method which let you set selected dates programmatically --> [Setting selected dates](https://github.com/Applandeo/Material-Calendar-View#setting-selected-dates)
* Now, the first day of a week depends on device location (thanks [thavelka](https://github.com/thavelka))
* Removed Glide dependency
* Added support for Drawable in EventDay object (You can set any drawable you want) --> [Adding events with icons](https://github.com/Applandeo/Material-Calendar-View/blob/master/README.md#adding-events-with-icons)
* Added ability to set header colours (background, label and arrows) programmatically --> [Colors customization](https://github.com/Applandeo/Material-Calendar-View#colors-customization)

#### Version 1.4.0:
* More color customization (abbreviations, calendar pages, labels colors)
* Changed onNavigationButtonClickListeners to onCalendarPageChangeListeners
* Added page change listeners to dialog pickers
* Added onDayClickListener to pickers (not dialog pickers)
* Added ability to insert list of disabled days

#### Version 1.3.2:
* Bug fixes

#### Version 1.3.1:
* Bug fixes

#### Version 1.3.0:
* Added ability to set minimum and maximum available date
* Added "Today" button to dialog picker

#### Version 1.2.0:
* Added many days picker
* Added range picker

#### Version 1.1.1:
* Added listeners for previous and forward arrow buttons

#### Version 1.1.0:
* Added build-in DatePicker dialog

#### Version 1.0.1:
* Bugs fixes

#### Version 1.0.0:
* Initial build

## Get in touch
It would be great if you decide to use our component in your project. It’s open source, feel free. Write to us at hi@applandeo.com if you want to be listed and we will include your app in our repo. If you have any questions or suggestions just let us know.

#### Apps using Material-Calendar-View:
* [FreeQuest](https://play.google.com/store/apps/details?id=com.applandeo.freequest)

## License
```
Copyright 2017, Applandeo sp. z o.o.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
