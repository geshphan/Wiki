// init
document.addEventListener('DOMContentLoaded', () => {
    // config
    const config = {
        isAscendingOrder: true,
    }

    const saveComment = form => {
        fetch('/api/save', {
            method: 'POST',
            body: new FormData(form)
        })
            .then(res => res.text())
            .then(res => console.log(res))
            .finally(() => loadCommentList());
    }

    const deleteComment = id => {
        fetch('/api/delete/' + id, {method: 'DELETE'})
            .then(res => res.text())
            .then(res => console.log(res))
            .finally(() => loadCommentList());
    }

    const toggleSortDirection = () => {
        config.isAscendingOrder = !config.isAscendingOrder;
    }

    const loadCommentList = () => {
        fetch('/api/list')
            .then(res => res.json())
            .then(res => {
                const container = document.querySelector('main');
                let html = '';

                res.sort((a, b) => {
                    if (config.isAscendingOrder) {
                        return Number(b.id) - Number(a.id);

                    } else {
                        return Number(a.id) - Number(b.id);
                    }
                });

                for (let comment of res) {
                    html += `<div>
                            <button id="${comment.id}" class="btn_delete">삭제</button>
                            <span>${comment.id} - ${comment.content}</span>
                        </div>`;
                }

                container.innerHTML = html;
            })
            .catch(err => alert(err));
    }

    document.querySelector('form').addEventListener('submit', function(event) {
        event.preventDefault();
        saveComment(this);
    });

    document.querySelector('main').addEventListener('click', event => {
        const isDeleteButton = event.target.classList.contains('btn_delete');
        if (!isDeleteButton) return;

        const id = event.target.id;
        deleteComment(id);
    });

    document.querySelector('.btn_sort').addEventListener('click', () => {
        toggleSortDirection();
        loadCommentList();
    });

    loadCommentList();
});

