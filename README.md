# Material-Calendar-View

It's a fully customized simple calendar and date picker created using Material Design guidelines.

![device-2017-05-29-072746](https://cloud.githubusercontent.com/assets/2614225/26537866/12b530f6-4443-11e7-910e-235b1267f2cd.png) ![device-2017-05-29-075947](https://cloud.githubusercontent.com/assets/2614225/26538163/e40730cc-4444-11e7-897d-ca45d7289a0f.png)

## Features
* Material Design
* Date picker mode
* Events icons
* Marks today


## How to use?
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
    compile 'com.applandeo:material-calendar-view:1.0.1'
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

CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);
calendarView.setEvents(events);
```

### Clicks handling:
```java
calendarView.setOnDayClickListener(new OnDayClickListener() {
    @Override
    public void onDayClick(EventDay eventDay) {
        Calendar clickedDayCalendar = eventDay.getCalendar();    
    }
});
```

### Getting a selected day in the picker mode:
```java
Calendar selectedDayCalendar = calendarView.getSelectedDate();
```

### Setting a current date:
```java
Calendar calendar = Calendar.getInstance();
calendar.set(2019, 7, 5);
        
calendarView.setDate(calendar);
```

## Customization
If you want to use calendar in the picker mode, in your XML layout set: ```app:datePicker="true"```

#### Colors customization:
* Header color: ```app:headerColor="[color]"```
* Header label color: ```app:headerLabelColor="[color]"```
* Previous button image resource: ```app:previousButtonSrc="[drawable]"```
* Forward button image resource: ```app:forwardButtonSrc="[drawable]"```
* Selection color in picker mode: ```app:selectionColor="[color]"```
* Today label color: ```app:todayLabelColor="[color]"```

#### Translations:
* Array of months names: ```app:monthsNames="[array]"``` (array must contains 12 names)
* Array of abbreviations of days of the week: ```app:daysNames="[array]"``` (array must contains 7 abbreviations)
