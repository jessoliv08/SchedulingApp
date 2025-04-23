import shared
import SwiftUI

struct IconTextView: View {
    let iconText: IconText
    
    var body: some View {
        HStack {
            if let uiImage = iconText.icon.toUIImage() {
                Image(uiImage: uiImage)
                    .resizable()
                    .frame(width: 24, height: 24)
                    .foregroundColor(Color.gray)
            }
            Text(iconText.text)
                .font(.body)
                .foregroundColor(Color.gray)
        }
    }
}
