class Article extends React.Component {
    render() {
        const article = this.props.article;
        const publishDate = article.publishDate.toString();
        // What if we showed the body inside the span directly instead of using the "dangerous" way?
        return  <div key={"detail_"+article.path} >
                    <h2>{article.title}</h2>
                    <i>{article.author} -- {publishDate}</i>
                    <span dangerouslySetInnerHTML={{__html:article.body}} />
                </div>
    }
};