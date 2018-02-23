class ArticleList extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            selected : false
        };
        this.handleSelection = this.handleSelection.bind(this);
    }
    
    handleSelection(key) {
        this.setState({
            selected: key
        });
    }
    
    getArticleMarkup() {
        const selectedArticle = this.props.list.find(a=>a.path === this.state.selected);
        if (selectedArticle) {
            return <Article article={selectedArticle}/>;
        } else {
            return <div>Please select an article to view it.</div>;
        }        
    }
    
    getArticleTitleMarkup(article) {
        // React requires that list items have a unique key on each item.  Why are we using Path?  How do we know that is unique?
        if (article.path === this.state.selected) {
            return <div key={article.path} className="article selected"><h3>{article.title}</h3></div>;
        } else {
            return <div key={article.path} className="article" onClick={e=>this.handleSelection(article.path)}><h3>{article.title}</h3></div>;
        }
    }
    
    render() {
        return (
            <div>
                <div className="article-list">
                    {this.props.list.map(article=>this.getArticleTitleMarkup(article))}
                </div>
                {this.getArticleMarkup()}
            </div>
        );
    };
};