let activities = JSON.parse(localStorage.getItem("activities")) || [];

function addActivity() {
    const exercise = document.getElementById("exercise").value;
    const duration = document.getElementById("duration").value;
    const calories = document.getElementById("calories").value;

    if (!exercise || !duration || !calories) {
        alert("Please fill all fields");
        return;
    }

    activities.push({
        exercise,
        duration,
        calories
    });

    localStorage.setItem("activities", JSON.stringify(activities));

    displayActivities();

    document.getElementById("exercise").value = "";
    document.getElementById("duration").value = "";
    document.getElementById("calories").value = "";
}

function displayActivities() {
    const list = document.getElementById("activityList");
    list.innerHTML = "";

    activities.forEach(activity => {
        const li = document.createElement("li");
        li.innerHTML =
            `<b>${activity.exercise}</b><br>
             Duration: ${activity.duration} min<br>
             Calories: ${activity.calories}`;
        list.appendChild(li);
    });
}

displayActivities();
