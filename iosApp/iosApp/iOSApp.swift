import SwiftUI

@main
struct iOSApp: App {
    @StateObject private var viewModelProvider = ViewModelProvider()

    var body: some Scene {
        WindowGroup {
            NavigationView {
                SelectDateView(
                    viewModelProvider: viewModelProvider
                )
            }
        }
    }
}
