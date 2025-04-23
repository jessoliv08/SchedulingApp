import SwiftUI
import shared

struct SelectDateView: View {
    @ObservedObject var viewModelProvider: ViewModelProvider
    @State private var navigate = false

    var body: some View {
        ZStack {
            VStack {
                Spacer().frame(height: 20)
                topBar()
                contentView()
                dynamicContent()

                NavigationLink(destination: SelectHourView(
                    viewModelProvider: viewModelProvider
                ), isActive: $navigate) {
                    EmptyView()
                }
                
                Spacer()
                bottomBar()
            }
        }.ignoresSafeArea()
    }
    
    func bottomBar() -> some View {
        Group {
            VStack(alignment: .leading, spacing: 8) {
                Text(viewModelProvider.selectDateViewModel.timeZoneTitle)
                    .font(.headline)
                IconTextView(iconText: viewModelProvider.selectDateViewModel.timeZone)
            }.padding(.horizontal)
            Spacer().frame(height: 20)
        }
    }
    
     func topBar() -> some View {
         Group {
            if let uiImage = viewModelProvider.selectDateViewModel.logo.toUIImage() {
                Image(uiImage: uiImage)
                    .resizable()
                    .scaledToFit()
                    .frame(width: 32, height: 32)
                    .padding()
            }
            Divider()
         }
    }
    
    func dynamicContent() -> some View {
        Group {
            Divider()
            
            Text(viewModelProvider.selectDateViewModel.selectDateTitle)
                .font(.headline)
            Spacer()
            Button("Go to Next Screen") {
                let formatter = DateFormatter()
                formatter.timeStyle = .medium
                let currentTime = formatter.string(from: Date())
                navigate = true
            }
            .buttonStyle(.borderless)
        }
    }
    
    private func contentView() -> some View  {
        Group {
            if let uiImage = viewModelProvider.selectDateViewModel.interviewerProfileImage.toUIImage() {
                Image(uiImage: uiImage)
                    .resizable()
                    .frame(width: 100, height: 100)
            }
            Text(viewModelProvider.selectDateViewModel.interviewerName)
                .font(.headline)
            Text(viewModelProvider.selectDateViewModel.interviewerDetail)
                .font(.title)
            
            VStack(alignment: .leading, spacing: 8) {
                IconTextView(iconText: viewModelProvider.selectDateViewModel.timeInfo)
                IconTextView(iconText: viewModelProvider.selectDateViewModel.callInfo)
            }.padding(.horizontal)
        }
    }
}





extension ImageResource {
    func toUIImage() -> UIImage? {
        UIImage(named: self.imageName)
    }
}
