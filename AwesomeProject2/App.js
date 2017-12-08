import React from 'react';
import { TextInput, Button, StyleSheet, Text, View, ScrollView, FlatList, AppRegistry, Picker } from 'react-native';
import { Repository } from "./repo.js";
import { StackNavigator } from 'react-navigation';
import * as Communications from 'react-native-communications';


class HomeScreen extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            refreshing: false,
        };
        this.onRefresh = this.onRefresh.bind(this);
        this.repository = new Repository();
    }

    static navigationOptions = {
        title: 'Welcome'
    };

    onRefresh() {
        this.setState({refreshing: true});
        this.setState({refreshing: false});
    }

    render() {
        let r_elems = this.repository.restaurants;
        let datas = [];
        for (let i = 0; i < r_elems.length; i++) {
            datas.push({key: i, value: r_elems[i]});
        }
        const { navigate } = this.props.navigation;
        return (
            <View>
                <ScrollView>
                    <FlatList
                        data={datas}
                        renderItem={({item}) =>
                            <View>
                                <Text style={styles.itemtitle}
                                      onPress={() => navigate('Edit', {
                                          obj: item.value,
                                          refreshing: this.onRefresh,
                                      })}>{item.value.title}</Text>
                                <Text style={styles.itemdetails}
                                      onPress={() => navigate('Edit', {
                                          obj: item.value,
                                          refreshing: this.onRefresh,
                                      })}>{item.value.details}</Text>
                                <Text style={styles.itemcategory}
                                      onPress={() => navigate('Edit', {
                                          obj: item.value,
                                          refreshing: this.onRefresh,
                                      })}>{item.value.category.name}</Text>
                                <Text></Text>
                            </View>
                        }
                    />
                </ScrollView>
                <Button style={styles.emailbutton} onPress={() => navigate('SendEmail')} title="Send E-mail" />
            </View>
        );
    }
}

class SendMailScreen extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            email: '',
            subject: '',
            content: '',
        }
    }

    render() {
        return (
            <View style={{alignItems: "center"}}>
                <TextInput
                    style={{marginTop: "5%", width: "90%", borderWidth: 1, backgroundColor: 'white'}}
                    onChangeText={(text) => this.setState({email: text})}
                    placeholder={"To"}
                />
                <TextInput
                    style={{marginTop: "5%", width: "90%",borderWidth: 1, backgroundColor: 'white'}}
                    onChangeText={(text) => this.setState({subject: text})}
                    placeholder={"Title"}
                />
                <TextInput
                    style={{marginTop: "5%", width: "90%", borderWidth: 1, backgroundColor: 'white'}}
                    onChangeText={(text) => this.setState({content: text})}
                    placeholder={"Content"}
                />
                <Button onPress={() => Communications.email([this.state.email, this.state.email], null, null, this.state.subject, this.state.content)} title={"Send"}/>
            </View>
        );
    }
}

class EditScreen extends React.Component {
    constructor(props) {
        super(props);
        const {state} = this.props.navigation;
        this.state = {
            name: state.params.obj.name,
            location: state.params.obj.location,
            stats: state.params.obj.stars,
        };
    }

    render() {
        const {state} = this.props.navigation;
        const {goBack} = this.props.navigation;
        return (
            <View style={{alignItems: "center"}}>
                <TextInput
                    style={{marginTop: "5%", width: "90%", borderWidth: 1, backgroundColor: 'white'}}
                    onChangeText={(text) => this.setState({name: text})}
                    value={this.state.name}
                />
                <TextInput
                    style={{marginTop: "5%", width: "90%",borderWidth: 1, backgroundColor: 'white'}}
                    onChangeText={(text) => this.setState({location: text})}
                    value={this.state.location}
                />
                <TextInput
                    style={{marginTop: "5%", width: "90%",borderWidth: 1, backgroundColor: 'white'}}
                    onChangeText={(text) => this.setState({stars: text})}
                    value={this.state.stars}
                />

                <Button onPress={() => {
                    let found = false;
                    let cat = null;
                    //for (let i = 0; i < state.params.categories.length; i++) {
                        //if (this.state.category === state.params.categories[i].name) {
                          //  found = true;
                          //  cat = state.params.categories[i];
                        //}
                   // }
                    if (found) {
                        state.params.obj.name = this.state.name;
                        state.params.obj.location = this.state.location;
                        state.params.obj.stars = this.state.stars;
                        state.params.refreshing();
                        goBack(null);
                    }

                }} title={"Save"}/>
            </View>

        );
    }
}

const NavigApp = StackNavigator({
    Home: { screen: HomeScreen },
    SendEmail: { screen: SendMailScreen},
    Edit: {screen: EditScreen},
});

export default class App extends React.Component{
    render() {
        return <NavigApp />;
    }
};

const styles = StyleSheet.create({
    container: {
        backgroundColor: '#fff',
    },
    itemtitle: {
        backgroundColor: 'steelblue',
    },
    itemdetails: {
        backgroundColor: 'skyblue',
    },
    itemcategory: {
        backgroundColor: 'powderblue',
    },
    emailbutton: {
        marginTop: '2%',
        marginBottom: '2%',
    }
});