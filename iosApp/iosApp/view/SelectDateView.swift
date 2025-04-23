import SwiftUI

struct SelectDateView: View {
    @ObservedObject var viewModelProvider: ViewModelProvider
    //@ObservedObject var stateViewModel: SharedViewModelWrapper
    @State private var navigate = false

    var body: some View {
        VStack {
            Text(viewModelProvider.selectDateViewModel.interviewerName)
                .font(.largeTitle)

            Button("Go to Next Screen") {
                let formatter = DateFormatter()
                formatter.timeStyle = .medium
                let currentTime = formatter.string(from: Date())
                navigate = true
            }
            .buttonStyle(.borderless)

            NavigationLink(destination: SelectHourView(
                viewModelProvider: viewModelProvider
            ), isActive: $navigate) {
                EmptyView()
            }
        }
    }
}
