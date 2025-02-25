import pandas as pd
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity

documents = pd.DataFrame({
    'title': ['Spring Boot Guide', 'AI in Search Engines', 'Understanding Elasticsearch'],
    'content': ['Learn how to build Spring Boot apps', 'AI is transforming search', 'Elasticsearch provides fast search']
})

def rank_documents(query):
    vectorizer = TfidfVectorizer()
    doc_vectors = vectorizer.fit_transform(documents['content'])
    query_vector = vectorizer.transform([query])

    scores = cosine_similarity(query_vector, doc_vectors).flatten()
    ranked_indices = scores.argsort()[::-1]
    
    return documents.iloc[ranked_indices]['title'].tolist()

query = "AI search"
print("Ranked Results:", rank_documents(query))
