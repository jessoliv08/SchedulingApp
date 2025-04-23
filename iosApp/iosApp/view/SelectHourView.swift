import SwiftUI

struct SelectHourView: View {
    @ObservedObject var viewModelProvider: ViewModelProvider
    @State private var navigate = false

    var body: some View {
        VStack {
            VStack(alignment: .leading, spacing: 8) {
                Text(viewModelProvider.selectHourViewModel.timeZoneTitle)
                    .font(.headline)
                IconTextView(iconText: viewModelProvider.selectHourViewModel.timeZone)
            }.padding(.horizontal)
            Divider().padding(.vertical, 20)
            Text(viewModelProvider.selectHourViewModel.selectHourTitle)
                .font(.headline)
            Text(viewModelProvider.selectHourViewModel.selectHourDescription)
                .font(.body)
            Spacer()
            Button("Go to Next Screen") {
                let formatter = DateFormatter()
                formatter.timeStyle = .medium
                let currentTime = formatter.string(from: Date())
                navigate = true
            }
            .buttonStyle(.borderless)
            
            NavigationLink(destination: SetupInterviewedView(
                viewModelProvider: viewModelProvider
            ), isActive: $navigate) {
                EmptyView()
            }
        }
        .navigationBarTitleDisplayMode(.inline)
    }
}
