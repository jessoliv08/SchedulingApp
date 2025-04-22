import SwiftUI

@main
struct iOSApp: App {
    @StateObject private var viewModel = SharedViewModelWrapper()
    
	var body: some Scene {
		WindowGroup {
            NavigationView {
                SelectDateView(viewModel: viewModel)
            }
		}
	}
}
