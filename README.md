# ORS Android Client

Android client library for the [OpenRouteService](https://openrouteservice.org) APIs.

## Dependency

The library is published on GitHub Packages.

```kotlin
repositories {
    maven {
        url = uri("https://maven.pkg.github.com/Pygmalion69/ors-android-client")
        credentials {
            username = System.getenv("GPR_USER")    // GitHub username
            password = System.getenv("GPR_TOKEN")   // Personal access token
        }
    }
}

dependencies {
    implementation("org.nitri.ors:ors-android-client:<latest-version>")
}
```

## Creating a client

```kotlin
val ors = Ors.create("<your_api_key>", context)
```

## Routing with domain objects

```kotlin
val request = RouteRequest(
    coordinates = listOf(
        listOf(8.681495, 49.41461),
        listOf(8.687872, 49.420318)
    )
)

val route: RouteResponse = ors.getRoute(Profile.DRIVING_CAR, request)
```

## Routing with the Kotlin DSL

```kotlin
val route = ors.getRoute(
    Profile.DRIVING_CAR,
    routeRequest {
        start(8.681495, 49.41461)
        end(8.687872, 49.420318)
    }
)
```

## Routing with helper classes

```kotlin
val helper = RouteHelper()
val route = with(helper) {
    ors.getRoute(
        start = 8.681495 to 49.41461,
        end = 8.687872 to 49.420318,
        profile = "driving-car"
    )
}
```

## API Reference

Complete an concise technical documentation: [API Reference](https://pygmalion.nitri.org/docs/ors-android-client/).
