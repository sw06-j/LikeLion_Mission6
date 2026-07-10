// ─── Assignment API ─────────────────────────────────────────────
const AssignmentAPI = {
    create: (memberId, data) => httpFetch('POST', `/members/${memberId}/assignments`, data),
    getAll: () => httpFetch('GET', '/assignments'),
    getByMember: (memberId) => httpFetch('GET', `/members/${memberId}/assignments`),
    getById: (id) => httpFetch('GET', `/assignments/${id}`),
    search: (keyword) => httpFetch('GET', `/assignments/search?keyword=${encodeURIComponent(keyword)}`),
    update: (id, data) => httpFetch('PUT', `/assignments/${id}`, data),
    delete: (id) => httpFetch('DELETE', `/assignments/${id}`),
};

// ─── 멤버 선택 드롭다운 채우기 ─────────────────────────────────
async function loadMemberSelects() {
    const members = await MemberAPI.getAll(null);
    if (!members) return;
    const opts = members.map(m => `<option value="${m.id}">${m.name} (${m.roleName})</option>`).join('');
    document.getElementById('assignment-member-id').innerHTML = '<option value="">멤버 선택</option>' + opts;
    document.getElementById('filter-member-id').innerHTML = '<option value="">멤버 선택</option>' + opts;
}

// ─── 과제 등록 ─────────────────────────────────────────────────
async function createAssignment() {
    const memberId = document.getElementById('assignment-member-id').value;
    const title = document.getElementById('assignment-title').value.trim();
    const description = document.getElementById('assignment-description').value.trim();

    if (!memberId) { showToast('멤버를 선택해주세요.', 'error'); return; }
    if (!title) { showToast('과제 제목을 입력해주세요.', 'error'); return; }

    const result = await AssignmentAPI.create(memberId, { title, description });
    if (result) {
        showToast('과제 등록 완료!', 'success');
        document.getElementById('assignment-title').value = '';
        document.getElementById('assignment-description').value = '';
        loadAllAssignments();
    }
}

// ─── 전체 과제 조회 ────────────────────────────────────────────
async function loadAllAssignments() {
    const assignments = await AssignmentAPI.getAll();
    if (assignments) renderAssignmentTable(assignments);
}

// ─── 멤버별 과제 조회 ──────────────────────────────────────────
async function loadAssignmentsByMember() {
    const memberId = document.getElementById('filter-member-id').value;
    if (!memberId) { showToast('멤버를 선택해주세요.', 'error'); return; }
    const assignments = await AssignmentAPI.getByMember(memberId);
    if (assignments) renderAssignmentTable(assignments);
}

// ─── 단건 조회 ─────────────────────────────────────────────────
async function loadAssignmentById() {
    const id = document.getElementById('search-assignment-id').value;
    if (!id) { showToast('과제 ID를 입력해주세요.', 'error'); return; }
    const assignment = await AssignmentAPI.getById(id);
    if (assignment) renderAssignmentTable([assignment]);
}

// ─── 제목 검색 ─────────────────────────────────────────────────
async function searchAssignments() {
    const keyword = document.getElementById('search-keyword').value.trim();
    if (!keyword) { showToast('검색어를 입력해주세요.', 'error'); return; }
    const assignments = await AssignmentAPI.search(keyword);
    if (assignments) renderAssignmentTable(assignments);
}

// ─── 과제 테이블 렌더링 ────────────────────────────────────────
function renderAssignmentTable(assignments) {
    const container = document.getElementById('assignment-list');
    if (!assignments.length) { container.innerHTML = '<p class="empty">과제가 없습니다.</p>'; return; }

    container.innerHTML = `
        <div class="table-wrap">
        <table>
            <thead><tr>
                <th>ID</th><th>제목</th><th>설명</th><th>멤버</th><th>관리</th>
            </tr></thead>
            <tbody>
            ${assignments.map(a => `
                <tr>
                    <td>${a.id}</td>
                    <td>${a.title}</td>
                    <td>${a.description || '-'}</td>
                    <td>${a.memberName} (ID: ${a.memberId})</td>
                    <td>
                        <button class="btn btn-secondary btn-sm" onclick="startEditAssignment(${a.id}, '${a.title.replace(/'/g, "\\'")}', '${(a.description || '').replace(/'/g, "\\'")}')">수정</button>
                        <button class="btn btn-danger btn-sm" onclick="deleteAssignment(${a.id})">삭제</button>
                    </td>
                </tr>
            `).join('')}
            </tbody>
        </table>
        </div>
    `;
}

// ─── 과제 수정 ─────────────────────────────────────────────────
function startEditAssignment(id, title, description) {
    document.getElementById('edit-assignment-id').value = id;
    document.getElementById('edit-assignment-title').value = title;
    document.getElementById('edit-assignment-description').value = description;
    document.getElementById('assignment-edit-card').style.display = '';
    document.getElementById('assignment-edit-card').scrollIntoView({ behavior: 'smooth' });
}

async function updateAssignment() {
    const id = document.getElementById('edit-assignment-id').value;
    const title = document.getElementById('edit-assignment-title').value.trim();
    const description = document.getElementById('edit-assignment-description').value.trim();

    if (!title) { showToast('제목을 입력해주세요.', 'error'); return; }

    const result = await AssignmentAPI.update(id, { title, description });
    if (result) {
        showToast('과제 수정 완료!', 'success');
        cancelEdit();
        loadAllAssignments();
    }
}

function cancelEdit() {
    document.getElementById('assignment-edit-card').style.display = 'none';
}

// ─── 과제 삭제 ─────────────────────────────────────────────────
async function deleteAssignment(id) {
    if (!confirm(`ID ${id} 과제를 삭제하시겠습니까?`)) return;
    await httpFetch('DELETE', `/assignments/${id}`);
    showToast('삭제 완료!', 'success');
    loadAllAssignments();
}
