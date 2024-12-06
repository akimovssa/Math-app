package com.example.mathapp.presentation.ui.about.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mathapp.R

@Composable
fun TextPreview() {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(15.dp, 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Lorem ipsum odor amet, consectetuer adipiscing elit. Vivamus morbi tincidunt quis taciti magna? Luctus tempus mollis platea non ultricies nisl tincidunt. Tincidunt mi tristique sem hendrerit luctus efficitur. Aptent per pretium maecenas posuere condimentum. Sociosqu pulvinar nullam netus fames sapien felis hendrerit nam sodales. Magnis et facilisis malesuada eros eros hac amet massa. Adipiscing tellus sed augue quis magnis odio ut.\n" +
                    "\n" +
                    "Magna convallis maximus lorem pulvinar sodales aptent metus rutrum. Donec fusce mauris risus elit suscipit, ac morbi condimentum sodales. Sociosqu sagittis venenatis taciti dignissim rutrum tortor feugiat sollicitudin vel. Cursus netus cras vivamus fermentum purus tortor. Nulla venenatis in dui id nulla elementum ad fringilla euismod. Ad imperdiet senectus ligula varius risus quis. Potenti accumsan placerat libero nunc imperdiet.\n" +
                    "\n" +
                    "Dis risus pretium varius, luctus himenaeos maecenas. Nisl semper conubia aliquam facilisis per cras. Bibendum scelerisque ac conubia rutrum dis sit. Non suscipit venenatis suscipit neque quam nisl magna varius. Nec mattis mauris neque amet hac nibh. Risus dui a inceptos penatibus turpis, ridiculus curabitur non.\n" +
                    "\n" +
                    "Vestibulum ligula sodales, aliquet tincidunt condimentum proin vitae. Dapibus suspendisse ante mi mattis conubia volutpat porttitor non fames. Ex sociosqu venenatis pellentesque leo nulla eu semper magnis. Dictum ultrices hac quam maximus dolor facilisis dis dolor vel. Et nisl ridiculus tempor sodales semper adipiscing. Nisl dolor facilisi ut congue primis.\n" +
                    "\n" +
                    "Convallis aenean ipsum enim rhoncus netus, bibendum purus massa. Velit adipiscing elementum class fermentum lobortis. Nascetur dapibus elementum inceptos aenean sociosqu netus. Finibus ac malesuada tempus proin volutpat integer maecenas. Nisl sit tincidunt suspendisse nostra ut auctor ultricies molestie. Tellus justo porttitor curae, nascetur non iaculis lacus. Massa vel urna ornare pharetra velit lectus. Ornare vulputate vehicula fermentum donec tempus praesent tellus. Mus faucibus porttitor; eu egestas dui platea libero.",
            fontSize = 16.sp,
            fontWeight = FontWeight.W400,
            color = colorResource(R.color.black_100)
        )
    }
}