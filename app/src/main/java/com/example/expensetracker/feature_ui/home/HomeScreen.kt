package com.example.expensetracker.feature_ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.expensetracker.R
import com.example.expensetracker.ui.theme.Zinc

@Composable
fun HomeScreen(){
    Surface(Modifier.fillMaxSize()) {
        ConstraintLayout (modifier = Modifier.fillMaxSize()){
            val (nameRow, list, card, topBar) = createRefs()
            Image(painter = painterResource(id = R.drawable.ic_topbar), contentDescription = null,
                modifier = Modifier.constrainAs(topBar){
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                })
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 64.dp, start = 35.dp, end = 35.dp)
                    .constrainAs(nameRow) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }){
                Column {
                    Text(text = "Heureux de vous revoir !" , fontSize = 16.sp, color = Color.White)
                    Text(
                        text = "E-Tracker",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.ic_notification),
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.CenterEnd)
                )
            }
            CardItem(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                .constrainAs(card) {
                    top.linkTo(nameRow.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
            TransactionList(modifier = Modifier
                .fillMaxWidth()
                .constrainAs(list) {
                    top.linkTo(card.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                    height = Dimension.fillToConstraints
                })
        }
    }
}

@Composable
fun CardItem(
    modifier: Modifier,
    //balance: String, income: String, expense: String
) {
    Column(modifier = modifier
        .padding(10.dp)
        .fillMaxWidth()
        .height(200.dp)
        .clip(RoundedCornerShape(16.dp))
        .background(Zinc)
        .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Column {
                Text(
                    text = "Balance Totale",
                    fontSize = 16.sp,
                    //style = Typography.titleMedium,
                    color = Color.White
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = "CDF 150000" ,
                    //style = Typography.headlineLarge,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                )
            }
            Image(
                painter = painterResource(id = R.drawable.dots_menu),
                contentDescription = null,
                modifier = Modifier.align(Alignment.CenterEnd)
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            CardRowItem(
                modifier = Modifier
                    .align(Alignment.CenterStart),
                title = "Solde",
                amount = "CDF 30350",
                imaget = R.drawable.ic_income
            )
            Spacer(modifier = Modifier.size(8.dp))
            CardRowItem(
                modifier = Modifier
                    .align(Alignment.CenterEnd),
                title = "Dépense",
                amount = "CDF 10350",
                imaget = R.drawable.ic_expense
            )
        }
    }
}

@Composable
fun TransactionList(modifier: Modifier){
    Column(modifier = modifier.padding(horizontal = 16.dp)){
        Box(modifier = Modifier.fillMaxWidth()){
            Text(
                text = "Dernières Transactions",
                fontSize = 20.sp
            )
            Text(
                text = "Voir plus",
                fontSize = 16.sp,
                modifier = Modifier.align(Alignment.CenterEnd)
            )
        }
        TransactionItem(
            title = "Netflix" ,
            amount = "- CDF 8500",
            icon = R.drawable.ic_netflix,
            date = "Aujourd'hui",
            color = Color.Red
        )
        TransactionItem(
            title = "Paypal" ,
            amount = "- CDF 8500",
            icon = R.drawable.ic_paypal,
            date = "Hier",
            color = Color.Red
        )
        TransactionItem(
            title = "Youtube" ,
            amount = "CDF 1000",
            icon = R.drawable.ic_youtube,
            date = "Il y a 2 jours",
            color = Color.Green
        )
        TransactionItem(
            title = "Upwork" ,
            amount = "CDF 1500",
            icon = R.drawable.ic_upwork,
            date = "Il y a 4 jours",
            color = Color.Green
        )
        TransactionItem(
            title = "Starbucks" ,
            amount = "- CDF 2500",
            icon = R.drawable.ic_starbucks,
            date = "Il y a 6 jours",
            color = Color.Red
        )
        TransactionItem(
            title = "Gameroom" ,
            amount = "- CDF 4500",
            icon = R.drawable.game,
            date = "Il y a 6 jours",
            color = Color.Red
        )
        TransactionItem(
            title = "Shopping" ,
            amount = "- CDF 9500",
            icon = R.drawable.shopping,
            date = "Il y a 6 jours",
            color = Color.Red
        )
        TransactionItem(
            title = "Paypal" ,
            amount = "- CDF 7500",
            icon = R.drawable.ic_paypal,
            date = "Il y a une semaine",
            color = Color.Green)
    }
}

@Composable
fun CardRowItem(modifier: Modifier, title: String, amount: String, imaget: Int) {
    Column(modifier = modifier) {
        Row {
            Image(
                painter = painterResource(id = imaget),
                contentDescription = null,
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = title,
                //style = Typography.bodyLarge,
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = amount,
            //style = Typography.titleLarge,
            color = Color.White)
    }
}

@Composable
fun TransactionItem(title: String, amount: String, icon: Int, date: String, color: Color) {
    Box(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
        Row {
            Image(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier.size(50.dp)
            )
            Spacer(modifier = Modifier.size(8.dp))
            Column {
                Text(
                    text = title, fontSize = 16.sp
                    //style = Typography.titleMedium,
                    //color = Color.White
                )
                Text(
                    text = date,
                    fontSize = 12.sp,
                    //style = Typography.titleMedium,
                    //color = Color.White
                )
            }
        }
        Text(
            text = amount, fontSize = 20.sp,
            modifier = Modifier.align(Alignment.CenterEnd),
            color = color
            //style = Typography.titleMedium,
            //color = Color.White
        )
    }
}


@Composable
@Preview(showBackground = true)
fun PreviewHomeScreen(){
    HomeScreen()
}