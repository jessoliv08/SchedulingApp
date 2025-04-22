import SwiftUI

struct SelectDateView: View {
    @ObservedObject var viewModel: SharedViewModelWrapper
    @State private var navigate = false

    var body: some View {
        VStack {
            Text("First Screen")
                .font(.largeTitle)

            Button("Go to Next Screen") {
                let formatter = DateFormatter()
                formatter.timeStyle = .medium
                let currentTime = formatter.string(from: Date())
                viewModel.setSelectedTime(currentTime)
                navigate = true
            }
            .buttonStyle(.borderless)

            NavigationLink(destination: SelectHourView(viewModel: viewModel), isActive: $navigate) {
                EmptyView()
            }
        }
    }
}
