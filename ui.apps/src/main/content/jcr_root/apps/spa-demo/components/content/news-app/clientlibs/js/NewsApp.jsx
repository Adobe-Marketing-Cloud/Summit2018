class NewsApp extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            error: null,
            isLoaded: false,
            items: []
        };
        // We can pass values in from the AEM component on html data attributes, are there other ways to do this?
        // What about OSGi configurations?
        this.apiPath = document.getElementById('root').attributes['data-app-path'].value;
        if (!this.apiPath.endsWith(".json")) {
            // Why append the extra suffix to get the app component instead of just getting the whole page?  Is there a difference?
            this.apiPath += "/jcr:content/root/responsivegrid.model.json"
        }
    }

    componentDidMount() {
        fetch(this.apiPath, {
            // Does the request work without this?  In Author?  In Publish?
            credentials: "same-origin",
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            }}
        ).then((response) => response.json())
        .then(result => {
            this.setState({
                isLoaded: true,
                // This is a little dense, but to summarize it calls parseFragment for all articles fragments in the response
                // and flattens them as simple javascript objects that the view objects can more easily leverage.
                // What does the original JSON structure look like compared to what parseFragment outputs?
                items: Object.entries(result[":items"])
                        .filter(item=>item[1].model === "spa-demo/models/article") // Ignore anything that isn't an article fragment
                        .map(nvp=>this.parseFragment.apply(this, nvp)) // Convert fragment to an article object
            });
        },
        error => {
            this.setState({
                isLoaded: false,
                error
            });
        });
    }

    parseFragment(name, fragment) {
        return {
            title: fragment.title,
            author: fragment.elements.author.value,
            description: fragment.description.value,
            path: name,
            publishDate: new Date(fragment.elements.published.value),
            tags: fragment.elements.keywords.value,
            body: fragment.elements.body.value
        };
    }

    render() {
        const {error, isLoaded, items} = this.state;
        if (error) {
            return <div>Error: {error.message}</div>;
        } else if (!isLoaded) {
            return <div>Loading...</div>;
        } else {
            return <ArticleList list={items}/>;
        }
    }
};


ReactDOM.render(
        <NewsApp/>,
        document.getElementById('root')
        );